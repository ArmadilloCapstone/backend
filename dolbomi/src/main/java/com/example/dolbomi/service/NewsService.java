package com.example.dolbomi.service;

import com.example.dolbomi.domain.News;
import com.example.dolbomi.repository.NewsRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsService {

    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository){
        this.newsRepository = newsRepository;
    }

    public List<News> getAllNewsByTeacherID(long id){
        return newsRepository.findAllByTeacherID(id);
    }

    public News createNews(News news){
        return newsRepository.save(news);
    }

    public ResponseEntity<News> getNews(Long no){
        News news = newsRepository.findById(no).orElseThrow(
                () -> new RuntimeException("Not exist Board Data by no : ["+no+"]"));
        return ResponseEntity.ok(news);
    }


    public ResponseEntity<Map<String, Boolean>> deleteNews(Long no) {
        News news = newsRepository.findById(no).orElseThrow(
                () -> new RuntimeException("Not exist Board Data by no : ["+no+"]"));
        newsRepository.delete(no);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted Board Data by id : ["+no+"]", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


}