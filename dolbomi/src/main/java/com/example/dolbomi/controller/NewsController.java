package com.example.dolbomi.controller;

import com.example.dolbomi.domain.News;
import com.example.dolbomi.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class NewsController {

    /*
    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }
     */
    private NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService){
        this.newsService = newsService;
    }

    @GetMapping("/BbsList")
    public List<News> getAllNews() {
        return newsService.getAllNews();
    }

    @PostMapping("/BbsList")
    public News createNews(@RequestBody News news){
        return newsService.createNews(news);
    }

    @GetMapping("/BbsList/{no}")
    public ResponseEntity<News> getNewsByNo(
            @PathVariable Long no){
        return newsService.getNews(no);
    }

    @DeleteMapping("/BbsList/{no}")
    public ResponseEntity<Map<String, Boolean>> deleteBoardByNo(
        @PathVariable Long no){
        return newsService.deleteNews(no);
    }


}
