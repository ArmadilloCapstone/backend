package com.example.dolbomi.service;

import com.example.dolbomi.domain.Album;
import com.example.dolbomi.repository.AlbumRepository;

import java.util.List;

public class AlbumService {
    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository){
        this.albumRepository = albumRepository;
    }

    public List<Album> getAllAlbumByTeacherID(long id){
        return albumRepository.findAllByTeacherID(id);
    }
}
