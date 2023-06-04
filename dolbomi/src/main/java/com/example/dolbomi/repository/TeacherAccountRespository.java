package com.example.dolbomi.repository;

import com.example.dolbomi.controller.TeacherLoginForm;
import com.example.dolbomi.domain.Teacher;

import java.util.List;

public interface TeacherAccountRespository {
    List<TeacherLoginForm> login(String user_id, String user_pw);
    String signup(String user_id, String user_pw, String name, String phone_num);
    Boolean changePw(String user_id, String user_pw, String user_new_pw);

}
