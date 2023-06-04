package com.example.dolbomi.service;

import com.example.dolbomi.domain.Message;
import com.example.dolbomi.domain.Parent;
import com.example.dolbomi.domain.Teacher;
import com.example.dolbomi.repository.MessageRepository;
import com.example.dolbomi.repository.NewsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }


    public Message createMessage(Message message){
        return messageRepository.save(message);
    }

    public List<Parent> getAllParentByTid(Long id) {
        return messageRepository.getAllParentByTid(id);
    }

    public List<Teacher> getAllTeacherByPid(Long id) {
        return messageRepository.getAllTeacherByPid(id);
    }

    public List<Message> getAllMessageByTid(String s) {
        return messageRepository.getAllMessageByTid(s);
    }

    public List<Message> getAllMessageByPid(String s) {
        return messageRepository.getAllMessageByPid(s);
    }
}
