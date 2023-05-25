package com.example.dolbomi.controller;

import com.example.dolbomi.domain.News;
import com.example.dolbomi.domain.Parent;
import com.example.dolbomi.domain.Teacher;
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

    @PostMapping("/BbsList")
    public List<News> getAllNews(@RequestBody Teacher teacher)
    {
        System.out.println("hi");
        System.out.println(newsService.getAllNewsByTeacherID(teacher.getId()).get(0).getDate());
        return newsService.getAllNewsByTeacherID(teacher.getId());
    }

    @PostMapping("/BbsList/create")
    public News createNews(@RequestBody PictureForm pictureForm){
        System.out.println(pictureForm);
        return null;
        //return newsService.createNews(news);
    }

    @PostMapping("/news/{no}")
    public ResponseEntity<News> getNewsByNo(
            @PathVariable Long no){
        return newsService.getNews(no);
    }

    @DeleteMapping("/news/{no}")
    public ResponseEntity<Map<String, Boolean>> deleteBoardByNo(
        @PathVariable Long no){
        return newsService.deleteNews(no);
    }


}
