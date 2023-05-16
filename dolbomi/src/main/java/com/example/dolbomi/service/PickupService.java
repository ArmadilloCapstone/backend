package com.example.dolbomi.service;

import com.example.dolbomi.controller.PickupRequestForm;
import com.example.dolbomi.controller.StudentPickupForm;
import com.example.dolbomi.domain.Guardian;
import com.example.dolbomi.domain.Parent;
import com.example.dolbomi.domain.Student;
import com.example.dolbomi.repository.ParentRepository;
import com.example.dolbomi.repository.PickupRepository;
import com.example.dolbomi.repository.StudentRepository;

import java.util.*;

public class PickupService {
    private final StudentRepository studentRepository;
    private final PickupRepository pickupRepository;
    private final ParentRepository parentRepository;

    public PickupService(StudentRepository studentRepository, PickupRepository pickupRepository,
                         ParentRepository parentRepository){
        this.studentRepository = studentRepository;
        this.pickupRepository = pickupRepository;
        this.parentRepository = parentRepository;
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

    public String requestPickupByParent(Long parent_id){
        PickupRequestForm pickupRequestForm = new PickupRequestForm();
        Optional<Parent> parent = parentRepository.findById(parent_id);
        if(parent.isPresent()){
            pickupRequestForm.setPickupManId(parent.get().getId());
            pickupRequestForm.setPickupManName(parent.get().getName());
        } else{
            System.out.println("학부모 정보가 없습니다");
            return null;
        }
        Long student_id = parent.get().getChild_id();
        Optional<Student> student = studentRepository.findById(student_id);
        if(student.isPresent()) {
            pickupRequestForm.setStudentId(student.get().getId());
            pickupRequestForm.setStudentName(student.get().getName());
            pickupRequestForm.setStudentGrade(student.get().getGrade());
            pickupRequestForm.setStudentGender(student.get().getGender());
            pickupRepository.saveByParent(pickupRequestForm);
        } else{
            System.out.println("돌봄학생 정보가 없습니다");
            return null;
        }
        return "success";
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
        pickupRepository.saveByGuardian(pickupRequestFormList);
        return pickupRequestFormList;
    }

    public List<PickupRequestForm> sendPickupForTeacher(){
        List<PickupRequestForm> pickupRequestFormList = pickupRepository.findAll();
        pickupRepository.clearPickupStore();
        return pickupRequestFormList;
    }

}
