package com.example.dolbomi.service;

import com.example.dolbomi.domain.News;
import com.example.dolbomi.repository.NewsRepository;
import com.example.dolbomi.repository.ParentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsService {

    private final NewsRepository newsRepository;
    private final ParentRepository parentRepository;

    public NewsService(NewsRepository newsRepository, ParentRepository parentRepository){
        this.newsRepository = newsRepository;
        this.parentRepository = parentRepository;
    }

    public List<News> getAllNewsByTeacherID(long id){
        return newsRepository.findAllByTeacherID(id);
    }
    public List<News> getAllNewsByParentID(long parent_id){
        long class_id = parentRepository.findById(parent_id).get().getClass_id();
        return newsRepository.findAllByClassID(class_id);
    }

    public List<News> searchNews(Long teacher_id, String keyword, String option) {
        return newsRepository.searchNews(teacher_id, keyword, option);
    }
    public List<News> searchNewsForParent(Long parent_id, String keyword, String option) {
        return newsRepository.searchNewsForParent(parent_id, keyword, option);
    }

    public News createNews(News news){
        return newsRepository.save(news);
    }

    public News updateNews(Long news_id, String title, String text, Boolean file_changed, List<MultipartFile> files) {
        return newsRepository.updateNews(news_id, title, text, file_changed, files);
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