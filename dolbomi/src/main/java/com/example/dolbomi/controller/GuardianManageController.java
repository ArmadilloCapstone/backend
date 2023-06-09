package com.example.dolbomi.controller;

import com.example.dolbomi.domain.Guardian;
import com.example.dolbomi.domain.Student;
import com.example.dolbomi.form.GuardianManageForm;
import com.example.dolbomi.service.GuardianManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class GuardianManageController {
    private GuardianManageService guardianManageService;

    @Autowired
    public GuardianManageController(GuardianManageService guardianManageService){
        this.guardianManageService = guardianManageService;
    }

    @PostMapping("/guardianManage/guardian_submit")
    public String addNewGuardian(@RequestBody Guardian guardian){
        return guardianManageService.addNewGuardian(guardian);
    }

    @PostMapping("/guardianManage/guardianList")
    public List<Guardian> sendGuardianList(){
        return guardianManageService.sendGuardianList();
    }

    @DeleteMapping("/guardianManage/guardian/{productId}")
    public void deleteGuardian(@PathVariable("productId") Long productId){
        guardianManageService.deleteGuardian(productId);
    }

    @PostMapping("/guardianManage/studentList")
    public List<Student> sendStudentForGuardian(@RequestBody Guardian guardian){
        return guardianManageService.sendStudentList(guardian.getId());
    }

    @PostMapping("/guardianManage/student_submit")
    public String addNewStudentUnderGuardian(@RequestBody GuardianManageForm guardianManageForm){
        //System.out.println(guardianManageForm.getStudentList().get(0).getName());
        return guardianManageService.addNewStudentUnderGuardian(guardianManageForm);
    }

    @DeleteMapping("/guardianManage/student/{guardian_id}/{student_id}")
    public void deleteStudentUnderGuardian(@PathVariable("guardian_id") Long guardian_id, @PathVariable("student_id")Long student_id){
        guardianManageService.deleteStudentUnderGuardian(guardian_id,student_id);
    }

    @PostMapping("/guardianManage")
    public List<GuardianManageForm> sendGuardianStudentList(){
        return guardianManageService.sendGuardianStudentList();
    }
}
