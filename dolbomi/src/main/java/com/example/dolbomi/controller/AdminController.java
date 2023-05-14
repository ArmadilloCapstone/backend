package com.example.dolbomi.controller;

import com.example.dolbomi.domain.DolbomClass;
import com.example.dolbomi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @PostMapping("/adminAddNewDolbomClass")
    public void addNewDolbomClass(@RequestBody DolbomClass dolbomClass){
        adminService.addNewDolbomClass(dolbomClass);
    }
    @PostMapping("/adminDeleteDolbomClass")
    public void deleteDolbomClass(@RequestBody DolbomClass dolbomClass){
        adminService.deleteDolbomClass(dolbomClass);
    }
}
