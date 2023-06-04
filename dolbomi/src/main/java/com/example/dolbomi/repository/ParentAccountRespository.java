package com.example.dolbomi.repository;

import com.example.dolbomi.domain.Parent;

import java.util.List;

public interface ParentAccountRespository {
    List<Parent> login(String user_id, String user_pw);
    String signup(String user_id, String user_pw, String name, String phone_num);
    Boolean changePw(String user_id, String user_pw, String user_new_pw);
}
