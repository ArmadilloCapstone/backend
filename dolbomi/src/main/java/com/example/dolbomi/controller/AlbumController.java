package com.example.dolbomi.controller;

import com.example.dolbomi.domain.Album;
import com.example.dolbomi.domain.News;
import com.example.dolbomi.domain.Teacher;
import com.example.dolbomi.service.AlbumService;
import com.example.dolbomi.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/GalleryList/create/nofile")
    public Album createAlbum(@RequestParam String title, @RequestParam String text,
                           @RequestParam Long teacher_id) throws IOException {
        Album album = new Album();
        album.setTitle(title);
        album.setContents(text);
        album.setWriter_id(teacher_id);
        album.setClass_id(teacher_id);
        album.setUploaded_date(Date.valueOf(LocalDate.now()));
        albumService.createAlbum(album);

        return album;
    }



}
