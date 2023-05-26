package com.example.dolbomi.controller;

import com.example.dolbomi.domain.News;
import com.example.dolbomi.domain.UploadedFile;
import com.example.dolbomi.domain.Parent;
import com.example.dolbomi.domain.Teacher;
import com.example.dolbomi.service.FileService;
import com.example.dolbomi.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Date;
import java.time.LocalDate;
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
    private FileService fileService;

    @Autowired
    public NewsController(NewsService newsService, FileService fileService){
        this.newsService = newsService;
        this.fileService = fileService;
    }

    @PostMapping("/BbsList")
    public List<News> getAllNews(@RequestBody Teacher teacher)
    {
        System.out.println("hi");
        System.out.println(newsService.getAllNewsByTeacherID(teacher.getId()).get(0).getDate());
        return newsService.getAllNewsByTeacherID(teacher.getId());
    }

    @PostMapping("/BbsList/create")
    public News createNews(@RequestParam String title, @RequestParam String text,
                           @RequestParam Long teacher_id, @RequestParam List<MultipartFile> files) throws IOException {
        News news = new News();
        news.setTitle(title);
        news.setText(text);
        news.setWriter_id(teacher_id);
        news.setClass_id(teacher_id);
        news.setDate(Date.valueOf(LocalDate.now()));
        newsService.createNews(news);



        for (MultipartFile file : files) {

            Long newsId = news.getId();

            if (!file.isEmpty()) {
                String fullPath = "C:\\" + file.getOriginalFilename();
                file.transferTo(new File(fullPath));

                fileService.saveFileInfo(newsId, file.getOriginalFilename());
            }
        }

        return news;

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



    @GetMapping("/upload")
    public String test(){
        return "test-form";
    }

    /*
    @PostMapping("/upload")
    public String addFile(@RequestParam String bbs_id, @RequestParam MultipartFile file) throws IOException {
        System.out.println("username = "+ bbs_id);

        if(!file.isEmpty()){
            String fullPath = "C:/images/"+file.getOriginalFilename();
            file.transferTo(new File(fullPath));
        }
        return "test-form";
    }
    */

}