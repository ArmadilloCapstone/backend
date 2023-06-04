package com.example.dolbomi.controller;

import com.example.dolbomi.domain.*;
import com.example.dolbomi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
public class AdminController {
    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/dolbom_class_submit")
    public String addNewDolbomClass(@RequestBody DolbomClass dolbomClass) {
        return adminService.addNewDolbomClass(dolbomClass);
    }

    @DeleteMapping("/dolbom_class/{productId}")
    public void deleteDolbomClass(@PathVariable("productId") Long productId) {
        adminService.deleteDolbomClass(productId);
    }

    @PostMapping("/dolbom_class")
    public List<DolbomClass> sendDolbomClass() {
        return adminService.sendDolbomClassList();
    }

    @PostMapping("/student_original_class")
    public List<Student> sendOriginal_class_num(){
        return adminService.sendOriginal_class_num();
    }

    @PostMapping("/student_submit")
    public String addNewStudentManageForm(@RequestBody StudentManageForm studentManageForm) {
        return adminService.addNewStudentManageForm(studentManageForm);
    }

    @PostMapping("/student_submit_csv")
    public void addNewStudentByCsv(@RequestParam("file") List<MultipartFile> files) {
        for (int i = 0; i < files.size(); i++) {
            adminService.addNewStudentByCsv(files.get(i));
        }
    }

    @DeleteMapping("/student/{productId}")
    public void deleteStudent(@PathVariable("productId") Long productId) {
        adminService.deleteStudent(productId);
    }

    @PostMapping("/student")
    public List<StudentManageForm> sendStudentManageFormList() {
       return adminService.sendStudentManageFormList();
    }

    @PostMapping("/student/dolbom_classList")
    public List<DolbomClass> sendDolbomClassForStudent() {
        return adminService.sendDolbomClassList();
    }

    @PostMapping("/teacher_submit")
    public String addNewTeacherManageForm(@RequestBody TeacherManageForm teacherManageForm) {
        return adminService.addNewTeacherManageForm(teacherManageForm);
    }

    @DeleteMapping("/teacher/{productId}")
    public void deleteTeacher(@PathVariable("productId") Long productId) {
        adminService.deleteTeacher(productId);
    }

    @PostMapping("/teacher")
    public List<TeacherManageForm> sendTeacher() {
        return adminService.sendTeacherList();
    }

    @PostMapping("/teacher/dolbom_classList")
    public List<DolbomClass> sendDolbomClassForTeacher() {
        return adminService.sendDolbomClassList();
    }

    @PostMapping("/parent_submit")
    public String addNewParentManageForm(@RequestBody ParentManageForm parentManageForm) {
        return adminService.addNewParentManageForm(parentManageForm);
    }

    @DeleteMapping("/parent/{productId}")
    public void deleteParent(@PathVariable("productId") Long productId) {
        adminService.deleteParent(productId);
    }

    @PostMapping("/parent")
    public List<ParentManageForm> sendParent() {
        return adminService.sendParentList();
    }

    @PostMapping("/parent/studentList")
    public List<Student> sendStudentForParent() {
        return adminService.sendStudentList();
    }

    @PostMapping("/after_school_class_submit")
    public String addNewAfterSchoolClass(@RequestBody AfterSchoolClassManageForm afterSchoolClassManageForm) {
        return adminService.addNewAfterSchoolClassManageForm(afterSchoolClassManageForm);
    }

    @DeleteMapping("/after_school_class/{productId}")
    public String deleteAfterSchoolClass(@PathVariable("productId") Long productId) {
        int result = adminService.deleteAfterSchoolClass(productId);
        if (result == 0) {
            return "해당 방과후활동을 듣고있는 학생이 있으므로 삭제할 수 없습니다";
        } else if (result == 1) {
            return "success";
        } else {
            return "존재하지 않는 방과후활동";
        }
    }

    @PostMapping("/after_school_class")
    public List<AfterSchoolClassManageForm> sendAfterSchoolClass() {
        return adminService.sendAfterSchoolClassManageForm();
    }

    @PostMapping("/student_schedule_submit")
    public String addNewStudentScheduleManageForm(@RequestBody StudentScheduleManageForm studentScheduleManageForm) {
        return adminService.addNewStudentScheduleManageForm(studentScheduleManageForm);
    }

    @DeleteMapping("/student_schedule/{productId}")
    public void deleteStudentSchedule(@PathVariable("productId") Long productId) {
        adminService.deleteStudentSchedule(productId);
    }

    @PostMapping("/student_schedule")
    public List<StudentScheduleManageForm> sendStudentSchedule() {
        return adminService.sendStudentSchedule();
    }

    @PostMapping("/student_schedule/studentList")
    public List<Student> sendStudentForStudentSchedule(){ return adminService.sendStudentList(); }

    @PostMapping("/student_schedule/AfterSchoolClassList")
    public List<AfterSchoolClassManageForm> sendAfterSchoolClassForStudentSchedule() { return adminService.sendAfterSchoolClassList(); }

    @PostMapping("/student_time_submit")
    public String addNewStudentTimeManageForm(@RequestBody StudentTimeManageForm studentTimeManageForm) {
        return adminService.addNewStudentTimeManageForm(studentTimeManageForm);
    }

    @DeleteMapping("/student_time/{productId}")
    public void deleteStudentTime(@PathVariable("productId") Long productId) {
        adminService.deleteStudentTime(productId);
    }

    @PostMapping("/student_time")
    public List<StudentTimeManageForm> sendStudentTimeManageForm() {
        return adminService.sendStudentTimeManageFormList();
    }

    @PostMapping("/student_time/studentList")
    public List<Student> sendStudentForStudentTime() { return adminService.sendStudentList(); }

}
