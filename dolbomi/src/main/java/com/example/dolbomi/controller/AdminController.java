package com.example.dolbomi.controller;

import com.example.dolbomi.domain.*;
import com.example.dolbomi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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
    public void deleteDolbomClass(@PathVariable("productId") Long productId){ adminService.deleteDolbomClass(productId); }
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
    @PostMapping("/student_dolbom_classList")
    public List<DolbomClass> sendDolbomClassForStudent() { return adminService.sendDolbomClassList(); }

    @PostMapping("/teacher_submit")
    public void addNewTeacher(@RequestBody TeacherManageForm teacherManageForm) { adminService.addNewTeacherManageForm(teacherManageForm); }
    @DeleteMapping("/teacher/{productId}")
    public void deleteTeacher(@PathVariable("productId") Long productId) { adminService.deleteTeacher(productId); }
    @PostMapping("/teacher")
    public List<TeacherManageForm> sendTeacher() { return adminService.sendTeacherList(); }

    @PostMapping("/parent_submit")
    public void addNewParent(@RequestBody ParentManageForm parentManageForm) { adminService.addNewParentManageForm(parentManageForm); }
    @DeleteMapping("/parent/{productId}")
    public void deleteParent(@PathVariable("productId") Long productId) { adminService.deleteParent(productId); }
    @PostMapping("/parent")
    public List<ParentManageForm> sendParent() { return adminService.sendParentList(); }

    @PostMapping("/after_school_class_submit")
    public void addNewAfterSchoolClass(@RequestBody AfterSchoolClass afterSchoolClass) { adminService.addNewAfterSchoolClass(afterSchoolClass); }
    @DeleteMapping("/after_school_class/{productId}")
    public String deleteAfterSchoolClass(@PathVariable("productId") Long productId) {
        int result = adminService.deleteAfterSchoolClass(productId);
        if(result == 0){
            return "해당 방과후활동을 듣고있는 학생이 있으므로 삭제할 수 없습니다";
        }
        else if(result == 1){
            return "삭제되었습니다";
        }
        else{
            return "존재하지 않는 방과후활동";
        }
    }
    @PostMapping("/after_school_class")
    public List<AfterSchoolClass> sendAfterSchoolClass() { return adminService.sendAfterSchoolClassList(); }

    @PostMapping("/student_schedule_submit")
    public void addNewStudentSchedule(@RequestBody StudentScheduleForm studentScheduleForm) { adminService.addNewStudentSchedule(studentScheduleForm);}
    @DeleteMapping("/student_schedule/{productId}")
    public void deleteStudentSchedule(@PathVariable("productId") Long productId) { adminService.deleteStudentSchedule(productId); }
    @PostMapping("/student_schedule")
    public List<StudentScheduleForm> sendStudentSchedule() { return adminService.sendStudentSchedule(); }

    @PostMapping("/student_time_submit")
    public void addNewStudentTime(@RequestBody StudentTime studentTime) { adminService.addNewStudentTime(studentTime); }
    @DeleteMapping("/student_time/{productId}")
    public void deleteStudentTime(@PathVariable("productId") Long productId) { adminService.deleteStudentTime(productId); }
    @PostMapping("/student_time")
    public List<StudentTime> sendStudentTime() { return adminService.sendStudentTimeList(); }

}
