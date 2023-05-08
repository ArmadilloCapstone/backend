package com.example.dolbomi.controller;

import com.example.dolbomi.domain.StudentState;
import com.example.dolbomi.service.StudentStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentStateController {
    private final StudentStateService studentStateService;
    @Autowired
    public StudentStateController(StudentStateService studentStateService){
        this.studentStateService = studentStateService;
    }

    @PostMapping("/changeStudentState")
    public String changeStudentState(){
        Long student_id = 20230001L;
        return studentStateService.changeState(20230001L, 2);
    }

    @PostMapping("/getStudentState")
    public List<StudentState> getStudentState(){
        return studentStateService.findAll();
    }

}
