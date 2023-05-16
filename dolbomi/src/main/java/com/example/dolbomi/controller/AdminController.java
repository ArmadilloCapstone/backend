package com.example.dolbomi.controller;

import com.example.dolbomi.domain.DolbomClass;
import com.example.dolbomi.domain.Student;
import com.example.dolbomi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class AdminController {
    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @PostMapping("/dolbom_class_submit")
    public void addNewDolbomClass(@RequestBody DolbomClass dolbomClass){
        adminService.addNewDolbomClass(dolbomClass);
    }
    @DeleteMapping("/dolbom_class/{productId}")
    public void deleteDolbomClass(@PathVariable("productId") Long productId){
        adminService.deleteDolbomClass(productId);
    }
    @PostMapping("/dolbom_class")
    public List<DolbomClass> sendDolbomClass(){
        return adminService.sendDolbomClassList();
    }

    @PostMapping("/student_submit")
    public void addNewStudent(@RequestBody Student student){ adminService.addNewStudent(student);}

    @PostMapping("/student_submit_csv")
    public void addNewStudentByCsv(@RequestParam("file") List<MultipartFile> files) {
        for(int i = 0; i < files.size(); i++){
            adminService.addNewStudentByCsv(files.get(i));
        }
    }

    @DeleteMapping("/student/{productId}")
    public void deleteStudent(@PathVariable("productId") Long productId){
        adminService.deleteStudent(productId);
    }
    @PostMapping("/student")
    public List<Student> sendStudent(){
        return adminService.sendStudentList();
    }



}
