package com.example.dolbomi.service;

import com.example.dolbomi.controller.CallForm;
import com.example.dolbomi.repository.TeacherRepository;

public class CallService {

    private final TeacherRepository teacherRepository;
    public CallService(TeacherRepository teacherRepository){
        this.teacherRepository = teacherRepository;
    }

    public CallForm sendTeacherPhoneNum(Long teacher_id){
        CallForm callForm = new CallForm();
        callForm.setTeacher_phone_num(teacherRepository.findById(teacher_id).get().getPhone_num());
        return callForm;
    }
}
