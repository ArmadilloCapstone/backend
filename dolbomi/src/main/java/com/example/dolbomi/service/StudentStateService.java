package com.example.dolbomi.service;

import com.example.dolbomi.domain.StudentState;
import com.example.dolbomi.repository.StudentStateRepository;

public class StudentStateService {
    private final StudentStateRepository studentStateRepository;

    public StudentStateService(StudentStateRepository studentStateRepository){
        this.studentStateRepository = studentStateRepository;
    }

    public String changeState(Long student_id, int state){
        if(studentStateRepository.changeState(student_id, state)) {
            return "변경완료";
        }
        else return "error";
    }
}
