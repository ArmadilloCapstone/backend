package com.example.dolbomi.controller;

import com.example.dolbomi.domain.Guardian;
import com.example.dolbomi.domain.Parent;
import com.example.dolbomi.domain.Student;
import com.example.dolbomi.service.PickupService;
import com.example.dolbomi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class HelloWorldController {
    private final StudentService studentService;

    @Autowired
    public HelloWorldController(StudentService studentService){
        this.studentService = studentService;
    }
//    @GetMapping("/hello")
//    public List<String> hello(){
//        return Arrays.asList("안녕하세요","hello");
//    }
    @GetMapping("/hello")
    public List<Student> hello(){
        return studentService.findMembers();
    }

}
