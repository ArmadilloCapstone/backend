package com.example.dolbomi.controller;

import com.example.dolbomi.domain.Guardian;
import com.example.dolbomi.domain.Parent;
import com.example.dolbomi.service.PickupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class PickupController {
    private final PickupService pickupService;
    @Autowired
    public PickupController(PickupService pickupService){
        this.pickupService = pickupService;
    }

    @PostMapping("/parent")
    public StudentPickupForm parentPickup(){
        Parent parent = new Parent();
        parent.setChild_id(20230001L);
        return pickupService.selectStudentForParent(parent); }

    @PostMapping("/guardian")
    public List<StudentPickupForm> guardianPickup(){
        Guardian guardian = new Guardian();
        guardian.setId(0001L);
        return pickupService.selectStudentForGuardian(guardian);
    }

    @PostMapping("/requestParent")
    public PickupRequestForm parentRequest(){
        Parent parent = new Parent();
        parent.setId(200L);
        parent.setName("박현숙");
        StudentPickupForm studentPickupForm = new StudentPickupForm();
        studentPickupForm.setId(150L);
        studentPickupForm.setName("박미희");
        studentPickupForm.setGrade(1L);
        studentPickupForm.setGender(1L);
        return pickupService.requestPickupByParent(parent,studentPickupForm);
    }


}
