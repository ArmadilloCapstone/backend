package com.example.dolbomi.service;

import com.example.dolbomi.controller.CallForm;
import com.example.dolbomi.domain.Teacher;
import com.example.dolbomi.repository.ParentRepository;
import com.example.dolbomi.repository.TeacherRepository;

import java.util.List;

public class CallService {

    private final ParentRepository parentRepository;
    private final TeacherRepository teacherRepository;
    public CallService(TeacherRepository teacherRepository, ParentRepository parentRepository){
        this.teacherRepository = teacherRepository;
        this.parentRepository = parentRepository;
    }

    public CallForm sendPhoneNum(Long parent_id){
        CallForm callForm = new CallForm();
        Long class_id = parentRepository.findById(parent_id).get().getClass_id();
        List<Teacher> teacherList = teacherRepository.findByClass_id(class_id);
        callForm.setTeacherList(teacherList);

        return callForm;
    }
}
