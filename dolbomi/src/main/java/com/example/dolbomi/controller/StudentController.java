package com.example.dolbomi.controller;

import com.example.dolbomi.domain.Student;
import com.example.dolbomi.domain.StudentState;
import com.example.dolbomi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/timeline")
public class StudentController {

    @Autowired
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/getStudent")
    public List<Student> getStudentInfo(){
        return studentService.findMembers();
    }
}
