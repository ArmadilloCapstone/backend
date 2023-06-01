package com.example.dolbomi.controller;

import com.example.dolbomi.domain.*;
import com.example.dolbomi.service.LoginService;
import com.example.dolbomi.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class MessageController {
    private final MessageService messageService;
    @Autowired
    public MessageController(MessageService messageService){
        this.messageService = messageService;
    }
/*
    @PostMapping("/getAllParentByTid")
    public List<Parent> getAllParentByTid(@RequestBody Teacher teacher){
    }

    @PostMapping("/getAllTeacherByPid")
    public List<Teacher> getAllTeacherByPid(@RequestBody Parent parent){
    }

    @PostMapping("/getAllMessageByTid")
    public List<Message> getAllMessageByTid(@RequestBody Teacher teacher){
    }

    @PostMapping("/getAllMessageByPid")
    public List<Message> getAllMessageByPid(@RequestBody Parent parent){
    }

 */
}
