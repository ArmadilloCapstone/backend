package com.example.dolbomi.repository;

import com.example.dolbomi.domain.Message;
import com.example.dolbomi.domain.News;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface MessageRepository {
    Optional<Message> findById(Long id);
    Message save(Message message);
    void delete(Long no);
}
