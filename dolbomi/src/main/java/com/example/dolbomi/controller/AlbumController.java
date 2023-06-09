package com.example.dolbomi.controller;

import com.example.dolbomi.domain.*;
import com.example.dolbomi.form.SearchForm;
import com.example.dolbomi.service.AlbumService;
import com.example.dolbomi.service.FileService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class AlbumController {
    private AlbumService albumService;
    private FileService fileService;

    @Autowired
    public AlbumController(AlbumService albumService, FileService fileService){
        this.albumService=albumService;
        this.fileService=fileService;
    }

    @PostMapping("/GalleryList")
    public List<Album> getAllAlbum(@RequestBody Teacher teacher)
    {
        //System.out.println("hi album");
        //System.out.println(albumService.getAllAlbumByTeacherID(teacher.getId()).get(0).getUploaded_date());
        return albumService.getAllAlbumByTeacherID(teacher.getId());
    }

    @PostMapping("/GalleryList/forParent")
    public List<Album> getAllAlbumForParent(@RequestBody Parent parent)
    {
        return albumService.getAllAlbumByParentID(parent.getId());
    }

    @PostMapping("/GalleryList/search")
    public List<Album> searchAlbum(@RequestBody SearchForm searchForm){
        // option="title" 제목검색
        // option="text 내용검색
        return albumService.searchAlbum(searchForm.getTeacher_id(), searchForm.getKeyword(), searchForm.getOption());
    }
    @PostMapping("/ParentGalleryList/search")
    public List<Album> searchAlbumForParent(@RequestBody SearchForm searchForm){
        // option="title" 제목검색
        // option="text 내용검색
        return albumService.searchAlbumForParent(searchForm.getParent_id(), searchForm.getKeyword(), searchForm.getOption());
    }

    @PostMapping("/GalleryList/create/file")
    public Album createAlbum(@RequestParam String title, @RequestParam String text,
                           @RequestParam Long teacher_id, @RequestParam List<MultipartFile> files) throws IOException {
        Album album = new Album();
        album.setTitle(title);
        album.setContents(text);
        album.setWriter_id(teacher_id);
        album.setClass_id(teacher_id);
        album.setUploaded_date(Date.valueOf(LocalDate.now()));
        for (MultipartFile file : files) {
            Long albumId = album.getId();
            if (!file.isEmpty()) {
                File folder = new File("C:\\build\\deploy\\build\\resources\\main\\static\\static\\media\\album\\"+title);
                folder.mkdirs();
                file.transferTo(new File(folder.getAbsolutePath()+"\\"+file.getOriginalFilename()));
                album.setFile_url(file.getOriginalFilename());
            }
        }
        albumService.createAlbum(album);
        return album;
    }

    @PostMapping("/GalleryList/update/nofile")
    public String updateAlbum(@RequestParam Long album_id, @RequestParam String title,
                              @RequestParam String text) throws IOException{
        albumService.updateAlbum(album_id, title, text, false, null);
        return "updated";
    }

    @PostMapping("/GalleryList/update/file")
    public String updateAlbum(@RequestParam Long album_id, @RequestParam String title,
                             @RequestParam String text, @RequestParam List<MultipartFile> files) throws IOException{
        albumService.updateAlbum(album_id, title, text, true, files);
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                File folder = new File("C:\\build\\deploy\\build\\resources\\main\\static\\static\\media\\album\\"+title);
                folder.mkdirs();
                file.transferTo(new File(folder.getAbsolutePath()+"\\"+file.getOriginalFilename()));
            }
        }
        return "updated";
    }

    @PostMapping("/album/{no}")
    public ResponseEntity<Album> getAlbumByNo(
            @PathVariable Long no){
        return albumService.getAlbum(no);
    }

    @RequestMapping("/download/album/{folder}/{file}")
    public void fileDownload(@PathVariable String folder, @PathVariable String file,
                             HttpServletResponse response) throws IOException {
        File f = new File("C:\\build\\deploy\\build\\resources\\main\\static\\static\\media\\album\\"+folder+"\\"+file);
        // file 다운로드 설정
        response.setContentType("application/download");
        response.setContentLength((int)f.length());
        response.setHeader("Content-disposition", "attachment;filename=\"" + file + "\"");
        // response 객체를 통해서 서버로부터 파일 다운로드
        OutputStream os = response.getOutputStream();
        // 파일 입력 객체 생성
        FileInputStream fis = new FileInputStream(f);
        FileCopyUtils.copy(fis, os);
        fis.close();
        os.close();
    }

    @DeleteMapping("/album/{no}")
    public ResponseEntity<Map<String, Boolean>> deleteBoardByNo(
            @PathVariable Long no){
        //fileService.removeFileById("album", no);
        return albumService.deleteAlbum(no);
    }



}
