package com.example.dolbomi.controller;

import com.example.dolbomi.domain.Parent;
import com.example.dolbomi.service.PickupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/parent")
    public StudentPickupForm parentPickup(){
        Parent parent = new Parent();
        parent.setChild_id(20230001L);
        return pickupService.selectStudentForParent(parent); }

    @GetMapping("/guardian")
    public List<StudentPickupForm> guardianPickup(){
        List<Long> studentList = new ArrayList<>();
        studentList.add(20230001L);
        studentList.add(20230003L);
        return pickupService.selectStudentForGuardian(studentList);
    }


}
