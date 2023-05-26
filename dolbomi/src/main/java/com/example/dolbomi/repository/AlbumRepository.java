package com.example.dolbomi.repository;

import com.example.dolbomi.domain.Album;

import java.util.List;

public interface AlbumRepository {

    public List<Album> findAllByTeacherID(Long id);
}
