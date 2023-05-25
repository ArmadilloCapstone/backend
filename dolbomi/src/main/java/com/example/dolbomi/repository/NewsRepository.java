package com.example.dolbomi.repository;

import com.example.dolbomi.domain.News;
import com.example.dolbomi.domain.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NewsRepository {

    List<News> findAllByTeacherID(Long id);
    Optional<News> findById(Long id);
    News save(News news);
    void delete(Long no);
}