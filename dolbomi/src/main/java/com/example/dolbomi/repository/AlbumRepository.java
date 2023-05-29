package com.example.dolbomi.repository;

import com.example.dolbomi.domain.Album;
import com.example.dolbomi.domain.News;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface AlbumRepository {

    public List<Album> findAllByTeacherID(Long id);

    List<Album> searchAlbum(Long teacher_id, String keyword, String option);

    Album save(Album album);

    Album updateAlbum(Long album_id, String title, String text, Boolean file_changed, List<MultipartFile> files);

    Optional<Album> findById(Long id);

    void delete(Long no);
}
