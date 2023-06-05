package com.example.dolbomi.service;

import com.example.dolbomi.domain.Album;
import com.example.dolbomi.domain.News;
import com.example.dolbomi.repository.AlbumRepository;
import com.example.dolbomi.repository.ParentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlbumService {
    private final AlbumRepository albumRepository;
    private final ParentRepository parentRepository;

    public AlbumService(AlbumRepository albumRepository, ParentRepository parentRepository){
        this.albumRepository = albumRepository;
        this.parentRepository = parentRepository;
    }

    public List<Album> getAllAlbumByTeacherID(long id){
        return albumRepository.findAllByTeacherID(id);
    }
    public List<Album> getAllAlbumByParentID(long parent_id){
        long class_id = parentRepository.findById(parent_id).get().getClass_id();
        return albumRepository.findAllByClassID(class_id);
    }

    public List<Album> searchAlbum(Long teacher_id, String keyword, String option) {
        return albumRepository.searchAlbum(teacher_id, keyword, option);
    }

    public List<Album> searchAlbumForParent(Long parent_id, String keyword, String option) {
        return albumRepository.searchAlbumForParent(parent_id, keyword, option);
    }
    public Album createAlbum(Album album){
        return albumRepository.save(album);
    }

    public Album updateAlbum(Long album_id, String title, String text, Boolean file_changed, List<MultipartFile> files) {
        return albumRepository.updateAlbum(album_id, title, text, file_changed, files);
    }

    public ResponseEntity<Album> getAlbum(Long no){
        Album album = albumRepository.findById(no).orElseThrow(
                () -> new RuntimeException("Not exist Board Data by no : ["+no+"]"));
        return ResponseEntity.ok(album);
    }

    public ResponseEntity<Map<String, Boolean>> deleteAlbum(Long no) {
        Album album = albumRepository.findById(no).orElseThrow(
                () -> new RuntimeException("Not exist Board Data by no : ["+no+"]"));
        albumRepository.delete(no);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted Board Data by id : ["+no+"]", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
