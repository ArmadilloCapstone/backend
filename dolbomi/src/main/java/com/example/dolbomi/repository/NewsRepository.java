package com.example.dolbomi.repository;

import com.example.dolbomi.domain.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Integer> {
}
