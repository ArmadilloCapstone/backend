package com.example.dolbomi.repository;

import com.example.dolbomi.domain.Admin;
import com.example.dolbomi.domain.Teacher;

import java.util.List;

public interface AdminRepository {
    List<Admin> login(String user_id, String user_pw);
    Boolean changePw(String user_id, String user_pw, String user_new_pw);

}
