package com.example.dolbomi.controller;

import com.example.dolbomi.domain.Guardian;
import com.example.dolbomi.domain.Parent;
import com.example.dolbomi.service.PickupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
    public List<StudentPickupForm> guardianPickup(@RequestBody Guardian guardian){
        return pickupService.selectStudentForGuardian(guardian);
    }

    @PostMapping("/requestParent")
    public String parentRequest(@RequestBody PickupRequestForm pickupRequestForm){
        return pickupService.requestPickupByParent(pickupRequestForm.getPickupManId());
    }

    @PostMapping("/requestGuardian")
    public String guardianRequest(@RequestBody PickupListRequestForm pickupListRequestForm){
        return pickupService.requestPickupByGuardian(pickupListRequestForm);
    }

    @PostMapping("/sendPickupFormToTeacher/{teacher_id}")
    public List<PickupRequestForm> teacherSend(@PathVariable("teacher_id") Long teacher_id){
        return pickupService.sendPickupForTeacher(teacher_id);
    }
}
