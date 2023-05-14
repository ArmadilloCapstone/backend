package com.example.dolbomi.controller;

import com.example.dolbomi.domain.StudentState;
import com.example.dolbomi.service.StudentStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String changeStudentState(@RequestBody StudentStateForm studentStateForm){
        System.out.println(studentStateForm);
        return studentStateService.changeState(studentStateForm.getStudent_id(), studentStateForm.getState());
    }

    @PostMapping("/getStudentInfo")
    public List<StudentStateForm> getStudentInfo(){
        return studentStateService.findMembers();
    }

}