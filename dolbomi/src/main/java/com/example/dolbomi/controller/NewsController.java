package com.example.dolbomi.controller;

import com.example.dolbomi.domain.News;
import com.example.dolbomi.domain.UploadedFile;
import com.example.dolbomi.domain.Parent;
import com.example.dolbomi.domain.Teacher;
import com.example.dolbomi.form.SearchForm;
import com.example.dolbomi.service.FileService;
import com.example.dolbomi.service.NewsService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class NewsController {


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
        //System.out.println("hi");
        //System.out.println(newsService.getAllNewsByTeacherID(teacher.getId()).get(0).getDate());
        return newsService.getAllNewsByTeacherID(teacher.getId());
    }
    @PostMapping("/BbsList/forParent")
    public List<News> getAllNewsForParent(@RequestBody Parent parent){
        return newsService.getAllNewsByParentID(parent.getId());
    }

    @PostMapping("/BbsList/search")
    public List<News> searchNews(@RequestBody SearchForm searchForm){
        // option="title" 제목검색
        // option="text 내용검색
        return newsService.searchNews(searchForm.getTeacher_id(), searchForm.getKeyword(), searchForm.getOption());
    }

    @PostMapping("/ParentBbsList/search")
    public List<News> searchNewsForParent(@RequestBody SearchForm searchForm){
        // option="title" 제목검색
        // option="text 내용검색
        return newsService.searchNewsForParent(searchForm.getParent_id(), searchForm.getKeyword(), searchForm.getOption());
    }
    @PostMapping("/BbsList/create/nofile")
    public News createNews(@RequestParam String title, @RequestParam String text,
                           @RequestParam Long teacher_id) throws IOException {
        News news = new News();
        news.setTitle(title);
        news.setText(text);
        news.setWriter_id(teacher_id);
        news.setClass_id(teacher_id);
        news.setDate(Date.valueOf(LocalDate.now()));
        newsService.createNews(news);

        return news;
    }

    @PostMapping("/BbsList/create/file")
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
                File folder = new File("C:\\build\\deploy\\build\\resources\\main\\static\\static\\media\\news\\"+title);
                folder.mkdirs();
                file.transferTo(new File(folder.getAbsolutePath()+"\\"+file.getOriginalFilename()));
                fileService.saveFileInfo(newsId, file.getOriginalFilename());
            }
        }

        return news;
    }

    @PostMapping("/BbsList/update/nofile")
    public String updateNews(@RequestParam Long news_id, @RequestParam String title,
                             @RequestParam String text) throws IOException{

        newsService.updateNews(news_id, title, text, false, null);

        return "updated";
    }
    @PostMapping("/BbsList/update/file")
    public String updateNews(@RequestParam Long news_id, @RequestParam String title,
                           @RequestParam String text, @RequestParam List<MultipartFile> files) throws IOException{
            newsService.updateNews(news_id, title, text, true, files);
            fileService.removeFileById("news", news_id);
            fileService.deleteFileInfo("news", news_id);
            for (MultipartFile file : files) {
                Long newsId = news_id;
                if (!file.isEmpty()) {
                    File folder = new File("C:\\build\\deploy\\build\\resources\\main\\static\\static\\media\\news\\"+title);
                    folder.mkdirs();
                    file.transferTo(new File(folder.getAbsolutePath()+"\\"+file.getOriginalFilename()));
                    fileService.saveFileInfo(newsId, file.getOriginalFilename());
                }
            }
        return "updated";
    }

    @PostMapping("/news/{no}")
    public ResponseEntity<News> getNewsByNo(
            @PathVariable Long no){
        return newsService.getNews(no);
    }

    @PostMapping("/news/files/{no}")
    public List<UploadedFile> getFilesByNo(
            @PathVariable Long no){
        return fileService.getFilesByNo(no);
    }
    @RequestMapping("/download/news/{folder}/{file}")
    public void fileDownload(@PathVariable String folder, @PathVariable String file,
                             HttpServletResponse response) throws IOException {
        File f = new File("C:\\build\\deploy\\build\\resources\\main\\static\\static\\media\\news\\"+folder+"\\"+file);
        // file 다운로드 설정
        response.setContentType("application/download");
        response.setContentLength((int)f.length());
        response.setHeader("Content-disposition", "attachment;filename=\"" + file + "\"");
        // response 객체를 통해서 서버로부터 파일 다운로드
        OutputStream os = response.getOutputStream();
        // 파일 입력 객체 생성
        FileInputStream fis = new FileInputStream(f);
        FileCopyUtils.copy(fis, os);
        fis.close();
        os.close();
    }

    @DeleteMapping("/news/{no}")
    public ResponseEntity<Map<String, Boolean>> deleteBoardByNo(
        @PathVariable Long no){
        fileService.removeFileById("news", no);
        fileService.deleteFileInfo("news", no);
        return newsService.deleteNews(no);
    }



    @GetMapping("/upload")
    public String test(){
        return "test-form";
    }


}