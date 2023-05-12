package com.example.dolbomi.controller;

import com.example.dolbomi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class AdminController {
    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @PostMapping("/admin")
    public List<StudentManageForm> searchStudentByAdmin(){
        return adminService.searchAllStudent();
    }
}
