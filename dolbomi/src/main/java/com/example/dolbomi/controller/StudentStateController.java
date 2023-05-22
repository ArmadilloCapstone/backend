package com.example.dolbomi.controller;

import com.example.dolbomi.domain.StudentState;
import com.example.dolbomi.service.StudentStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
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

    @PostMapping("/sendStudentStateToParent/{parent_id}")
    public StudentStateForm sendStudentStateToParent(@PathVariable("parent_id") Long parent_id){
        return studentStateService.sendStudentState(parent_id);
    }

    @PostMapping("/getStudentInfo")
    public List<StudentStateForm> getStudentInfo(){
        return studentStateService.findMembers();
    }

}
