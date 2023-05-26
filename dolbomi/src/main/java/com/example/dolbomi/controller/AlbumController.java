package com.example.dolbomi.controller;

import com.example.dolbomi.domain.Album;
import com.example.dolbomi.domain.News;
import com.example.dolbomi.domain.Teacher;
import com.example.dolbomi.service.AlbumService;
import com.example.dolbomi.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
