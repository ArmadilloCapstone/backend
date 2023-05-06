package com.example.dolbomi.service;

import com.example.dolbomi.controller.PickupRequestForm;
import com.example.dolbomi.controller.StudentPickupForm;
import com.example.dolbomi.domain.Guardian;
import com.example.dolbomi.domain.Parent;
import com.example.dolbomi.domain.Student;
import com.example.dolbomi.repository.StudentRepository;

import java.util.*;

public class PickupService {
    private final StudentRepository studentRepository;

    public PickupService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }
    public StudentPickupForm selectStudentForParent(Parent parent){
        StudentPickupForm studentPickupForm = new StudentPickupForm();
        Optional<Student> result = studentRepository.findById(parent.getChild_id());
        studentPickupForm.setId(result.get().getId());
        studentPickupForm.setName(result.get().getName());
        studentPickupForm.setGrade(result.get().getGrade());
        studentPickupForm.setGender(result.get().getGender());
        return studentPickupForm;
    }

    public List<StudentPickupForm> selectStudentForGuardian(Guardian guardian){
        List<StudentPickupForm> studentPickupFormList = new ArrayList<>();
        List<Student>result =  studentRepository.findStudent_idById(guardian.getId());
        int studentCount = result.size();
        for(int i = 0; i<studentCount;i++){
            StudentPickupForm studentPickupForm = new StudentPickupForm();
            studentPickupForm.setId(result.get(i).getId());
            studentPickupForm.setName(result.get(i).getName());
            studentPickupForm.setGrade(result.get(i).getGrade());
            studentPickupForm.setGender(result.get(i).getGender());
            studentPickupFormList.add(studentPickupForm);
        }
        return studentPickupFormList;
    }

    public PickupRequestForm requestPickupByParent(Parent parent, StudentPickupForm studentPickupForm){
        PickupRequestForm pickupRequestForm = new PickupRequestForm();
        pickupRequestForm.setPickupManId(parent.getId());
        pickupRequestForm.setPickupManName(parent.getName());
        pickupRequestForm.setStudentId(studentPickupForm.getId());
        pickupRequestForm.setStudentName(studentPickupForm.getName());
        pickupRequestForm.setStudentGrade(studentPickupForm.getGrade());
        pickupRequestForm.setStudentGender(studentPickupForm.getGender());
        return pickupRequestForm;
    }

    public List<PickupRequestForm> requestPickupByGuardian(Guardian guardian, List<StudentPickupForm> studentPickupFormList){
        List<PickupRequestForm> pickupRequestFormList = new ArrayList<>();
        int studentCount = studentPickupFormList.size();
        for(int i = 0; i<studentCount;i++){
            PickupRequestForm pickupRequestForm = new PickupRequestForm();
            pickupRequestForm.setPickupManId(guardian.getId());
            pickupRequestForm.setPickupManName(guardian.getName());
            pickupRequestForm.setStudentId(studentPickupFormList.get(i).getId());
            pickupRequestForm.setStudentName(studentPickupFormList.get(i).getName());
            pickupRequestForm.setStudentGrade(studentPickupFormList.get(i).getGrade());
            pickupRequestForm.setStudentGender(studentPickupFormList.get(i).getGender());
            pickupRequestFormList.add(pickupRequestForm);
        }
        return pickupRequestFormList;
    }

    private static boolean guardianChildIdEqualToStudentId(List<Long> studentIdList, List<Student> studentList, int i, int j) {
        return studentList.get(i).getId().equals(studentIdList.get(j));
    }
}
