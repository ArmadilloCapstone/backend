package com.example.dolbomi.controller;

import com.example.dolbomi.domain.Guardian;
import com.example.dolbomi.domain.Parent;
import com.example.dolbomi.service.PickupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController
public class PickupController {
    private final PickupService pickupService;
    @Autowired
    public PickupController(PickupService pickupService){
        this.pickupService = pickupService;
    }

    @PostMapping("/parentPickup")
    public StudentPickupForm parentPickup(){
        Parent parent = new Parent();
        parent.setChild_id(20230001L);
        return pickupService.selectStudentForParent(parent); }

    /*
    @PostMapping("/parent")
    public StudentPickupForm parentPickup(@RequestBody Parent parent){
        return pickupService.selectStudentForParent(parent); }
    */

    @PostMapping("/guardian")
    public List<StudentPickupForm> guardianPickup(){
        Guardian guardian = new Guardian();
        guardian.setId(0001L);
        return pickupService.selectStudentForGuardian(guardian);
    }
    /*
    @PostMapping("/guardian")
    public List<StudentPickupForm> guardianPickup(@RequestBody Guardian guardian){
        return pickupService.selectStudentForGuardian(guardian);
    }
    */
    @PostMapping("/requestParent")
    public PickupRequestForm parentRequest(@RequestBody PickupRequestForm pickupRequestForm){
        return pickupService.requestPickupByParent(pickupRequestForm.getPickupManId());
    }

    @PostMapping("/requestGuardian")
    public List<PickupRequestForm> guardianRequest(){
        Guardian guardian = new Guardian();
        guardian.setId(100L);
        guardian.setName("이수성");

        List<StudentPickupForm> studentPickupFormList = new ArrayList<>();
        StudentPickupForm studentPickupForm1 = new StudentPickupForm();
        studentPickupForm1.setId(20L);
        studentPickupForm1.setName("강하늘");
        studentPickupForm1.setGrade(1L);
        studentPickupForm1.setGender(1L);

        StudentPickupForm studentPickupForm2 = new StudentPickupForm();
        studentPickupForm2.setId(30L);
        studentPickupForm2.setName("최익현");
        studentPickupForm2.setGrade(2L);
        studentPickupForm2.setGender(2L);

        studentPickupFormList.add(studentPickupForm1);
        studentPickupFormList.add(studentPickupForm2);
        return pickupService.requestPickupByGuardian(guardian, studentPickupFormList);
    }
    /*
    @PostMapping("/requestGuardian")
    public List<PickupRequestForm> guardianRequest(@RequestBody Guardian guardian,
                                                   @RequestBody List<StudentPickupForm> studentPickupFormList){
        return pickupService.requestPickupByGuardian(guardian, studentPickupFormList);
    }
    */
    @PostMapping("/sendPickupFormToTeacher")
    public List<PickupRequestForm> teacherSend(){
        return pickupService.sendPickupForTeacher();
    }
}
