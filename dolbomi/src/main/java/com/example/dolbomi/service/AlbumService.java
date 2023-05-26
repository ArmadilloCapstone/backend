package com.example.dolbomi.service;

import com.example.dolbomi.domain.Album;
import com.example.dolbomi.domain.News;
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

    public List<Album> searchAlbum(Long teacher_id, String keyword, String option) {
        return albumRepository.searchAlbum(teacher_id, keyword, option);
    }

    public Album createAlbum(Album album){
        return albumRepository.save(album);
    }
}
