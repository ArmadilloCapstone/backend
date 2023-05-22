package com.example.dolbomi.controller;

import com.example.dolbomi.domain.News;
import com.example.dolbomi.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class NewsController {

    private NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService){
        this.newsService = newsService;
    }

    @GetMapping("/news")
    public List<News> getAllNews() {
        return newsService.getAllNews();
    }

    @PostMapping("/news")
    public News createNews(@RequestBody News news){
        return newsService.createNews(news);
    }

    @GetMapping("/news/{no}")
    public ResponseEntity<News> getNewsByNo(
            @PathVariable Integer no){
        return newsService.getNews(no);
    }

    @DeleteMapping("/news/{no}")
    public ResponseEntity<Map<String, Boolean>> deleteBoardByNo(
        @PathVariable Integer no){
        return newsService.deleteNews(no);
    }


}
