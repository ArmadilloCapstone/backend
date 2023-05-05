package com.example.dolbomi.service;

import com.example.dolbomi.controller.ParentPickupRequestForm;
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
        studentPickupForm.setName(result.get().getName());
        studentPickupForm.setGrade(result.get().getGrade());
        studentPickupForm.setGender(result.get().getGender());
        return studentPickupForm;
    }
    public List<StudentPickupForm> selectStudentForGuardian(List<Long> studentIdList){
        List<Student> studentList = studentRepository.findAll();
        List<StudentPickupForm> studentPickupFormList = new ArrayList<>();
        int studentCount = studentList.size();
        int studentIdCount = studentIdList.size();
        for(int i = 0; i < studentCount; i++){
            for(int j = 0; j<studentIdCount;j++){
                if(guardianChildIdEqualToStudentId(studentIdList, studentList, i, j)){
                    StudentPickupForm studentPickupForm = new StudentPickupForm();
                    studentPickupForm.setName(studentList.get(i).getName());
                    studentPickupForm.setGrade(studentList.get(i).getGrade());
                    studentPickupForm.setGender(studentList.get(i).getGender());
                    studentPickupFormList.add(studentPickupForm);
                    break;
                }
            }
        }
        return studentPickupFormList;
    }
    public ParentPickupRequestForm requestPickupByParent(String parentName, StudentPickupForm studentPickupForm){
        ParentPickupRequestForm parentPickupRequestForm = new ParentPickupRequestForm();
        parentPickupRequestForm.setParentName(parentName);
        parentPickupRequestForm.setStudentName(studentPickupForm.getName());
        parentPickupRequestForm.setStudentGrade(studentPickupForm.getGrade());
        parentPickupRequestForm.setStudentGender(studentPickupForm.getGender());
        return parentPickupRequestForm;
    }



    private static boolean guardianChildIdEqualToStudentId(List<Long> studentIdList, List<Student> studentList, int i, int j) {
        return studentList.get(i).getId().equals(studentIdList.get(j));
    }
}
