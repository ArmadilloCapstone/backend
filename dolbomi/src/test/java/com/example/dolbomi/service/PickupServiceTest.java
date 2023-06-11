package com.example.dolbomi.service;

import com.example.dolbomi.form.StudentPickupForm;
import com.example.dolbomi.domain.Parent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class PickupServiceTest {

    @Autowired PickupService pickupService;

    @Test
    void selectStudentForParent() {
        Parent parent = new Parent();
        parent.setChild_id(20230003L);
        StudentPickupForm studentPickupForm = pickupService.selectStudentForParent(parent);
        //System.out.println(studentPickupForm.getName());
        //System.out.println(studentPickupForm.getGrade());
        //System.out.println(studentPickupForm.getGender());
    }

//    @Test
//    void selectStudentForGuardian() {
//        List<Long> studentList = new ArrayList<>();
//        studentList.add(20230001L);
//        studentList.add(20230003L);
//        List<StudentPickupForm> studentPickupFormList = pickupService.selectStudentForGuardian(studentList);
//        //System.out.println(studentPickupFormList.get(0).getName());
//        //System.out.println(studentPickupFormList.get(1).getName());
//    }

}