package com.example.dolbomi.service;

import com.example.dolbomi.domain.News;
import com.example.dolbomi.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

public class NewsService {

    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository){
        this.newsRepository = newsRepository;
    }

    public List<News> getAllNews(){
        return newsRepository.findAll();
    }
}
