package com.example.dolbomi.repository;

import com.example.dolbomi.domain.Message;
import com.example.dolbomi.domain.News;
import com.example.dolbomi.domain.Parent;
import com.example.dolbomi.domain.Teacher;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface MessageRepository {
    Optional<Message> findById(Long id);
    Message save(Message message);
    void delete(Long no);

    List<Parent> getAllParentByTid(Long id);

    List<Teacher> getAllTeacherByPid(Long id);

    List<Message> getAllMessageByTid(String s);

    List<Message> getAllMessageByPid(String s);
}
