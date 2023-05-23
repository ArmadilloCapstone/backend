package com.example.dolbomi.controller;

import com.example.dolbomi.domain.Student;
import com.example.dolbomi.domain.StudentState;
import com.example.dolbomi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class StudentController {

    @Autowired
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @PostMapping("/getStudent/{parent_id}")
    public Student getStudentInfo(@PathVariable("parent_id") Long parent_id){
        return studentService.findStudent_idByParentId(parent_id);
    }

    @PostMapping("/getStudent")
    public List<Student> getStudentInfo(){
        return studentService.findMembers();
    }
}
