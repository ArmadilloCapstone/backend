package com.example.dolbomi.controller;

import com.example.dolbomi.domain.Album;
import com.example.dolbomi.domain.News;
import com.example.dolbomi.domain.Teacher;
import com.example.dolbomi.service.AlbumService;
import com.example.dolbomi.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

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
        System.out.println("hi album");
        System.out.println(albumService.getAllAlbumByTeacherID(teacher.getId()).get(0).getUploaded_date());
        return albumService.getAllAlbumByTeacherID(teacher.getId());
    }

    @PostMapping("/GalleryList/search")
    public List<Album> searchAlbum(@RequestBody SearchForm searchForm){
        // option="title" 제목검색
        // option="text 내용검색
        return albumService.searchAlbum(searchForm.getTeacher_id(), searchForm.getKeyword(), searchForm.getOption());
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
        albumService.createAlbum(album);

        for (MultipartFile file : files) {
            Long newsId = album.getId();
            if (!file.isEmpty()) {
                String fullPath = "C:\\build\\resources\\main\\static\\static\\media\\album\\" + file.getOriginalFilename();
                file.transferTo(new File(fullPath));
                fileService.saveFileInfo(newsId, file.getOriginalFilename());
            }
        }
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
        fileService.removeFileById(album_id);
        fileService.deleteFileInfo(album_id);
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String fullPath = "C:\\build\\resources\\main\\static\\static\\media\\album\\" + file.getOriginalFilename();
                file.transferTo(new File(fullPath));
                fileService.saveFileInfo(album_id, file.getOriginalFilename());
            }
        }
        return "updated";
    }



}
