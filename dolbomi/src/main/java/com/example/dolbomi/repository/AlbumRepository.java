package com.example.dolbomi.repository;

import com.example.dolbomi.domain.Album;
import com.example.dolbomi.domain.News;

import java.util.List;

public interface AlbumRepository {

    public List<Album> findAllByTeacherID(Long id);

    List<Album> searchAlbum(Long teacher_id, String keyword, String option);

    Album save(Album album);
}
