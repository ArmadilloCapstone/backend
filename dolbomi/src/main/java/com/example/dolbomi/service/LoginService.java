package com.example.dolbomi.service;

import com.example.dolbomi.domain.*;
import com.example.dolbomi.repository.*;

import java.util.List;
import java.util.Optional;

public class LoginService {

    private final TeacherAccountRespository teacherAccountRespository;
    private final ParentAccountRespository parentAccountRespository;
    private final GuardianRepository guardianRepository;
    private final AdminRepository adminRepository;

    public LoginService(TeacherAccountRespository teacherAccountRespository, ParentAccountRespository parentAccountRespository, GuardianRepository guardianRepository, AdminRepository adminRepository) {
        this.teacherAccountRespository = teacherAccountRespository;
        this.parentAccountRespository = parentAccountRespository;
        this.guardianRepository = guardianRepository;
        this.adminRepository = adminRepository;
    }

    public List<Teacher> LoginT(String user_id, String user_pw) {
        return teacherAccountRespository.login(user_id, user_pw);
    }
    public Boolean signupT(String user_id, String user_pw, String name)
    {
        return teacherAccountRespository.signup(user_id, user_pw, name);

    }
    public Boolean changePwT(String user_id, String user_pw, String user_new_pw)
    {
        return teacherAccountRespository.changePw(user_id, user_pw, user_new_pw);

    }

    public List<Parent> LoginP(String user_id, String user_pw) {
        return parentAccountRespository.login(user_id, user_pw);
    }
    public Boolean signupP(String user_id, String user_pw, String name)
    {
        return parentAccountRespository.signup(user_id, user_pw, name);

    }
    public Boolean changePwP(String user_id, String user_pw, String user_new_pw)
    {
        return parentAccountRespository.changePw(user_id, user_pw, user_new_pw);

    }

    public List<Guardian> LoginG(Long serial_num) {
        return guardianRepository.login(serial_num);
    }
    public Boolean signupG(Long serial_num, String name, String info)
    {
        return guardianRepository.signup(serial_num, name, info);

    }

    public List<Admin> LoginA(String user_id, String user_pw) {
        return adminRepository.login(user_id, user_pw);
    }
    public Boolean changePwA(String user_id, String user_pw, String user_new_pw)
    {
        return adminRepository.changePw(user_id, user_pw, user_new_pw);

    }

}
