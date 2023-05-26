package com.example.dolbomi.repository;

import com.example.dolbomi.domain.News;
import com.example.dolbomi.domain.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface NewsRepository {

    List<News> findAllByTeacherID(Long id);
    List<News> searchNews(Long teacher_id, String keyword, String option);
    Optional<News> findById(Long id);
    News save(News news);
    void delete(Long no);
    News updateNews(Long news_id, String title, String text, Boolean file_changed, List<MultipartFile> files);
}