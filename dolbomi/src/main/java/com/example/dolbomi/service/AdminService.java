package com.example.dolbomi.service;

import com.example.dolbomi.domain.*;
import com.example.dolbomi.repository.*;

import java.util.List;

public class AdminService {
    private final DolbomClassRepository dolbomClassRepository;
    private final StudentRepository studentRepository;
    private final ParentRepository parentRepository;
    private final TeacherRepository teacherRepository;

    public AdminService(DolbomClassRepository dolbomClassRepository, StudentRepository studentRepository,
                        ParentRepository parentRepository, TeacherRepository teacherRepository) {
        this.dolbomClassRepository = dolbomClassRepository;
        this.studentRepository = studentRepository;
        this.parentRepository = parentRepository;
        this.teacherRepository = teacherRepository;
    }
    public void addNewDolbomClass(DolbomClass dolbomClass){
        List<DolbomClass> result = dolbomClassRepository.findByClassName(dolbomClass.getClass_name(), dolbomClass.getClass_num());
        if((result.size() == 1) && (result.get(0).getDisable() == 0)){
            System.out.println("기존 돌봄학급 활성화");
            dolbomClassRepository.activationDolbomClass(result.get(0).getId());
        } else if ( (result.size() == 1) && (result.get(0).getDisable() == 1)) {
            System.out.println("이미 활성화된 돌봄학급입니다");
        } else if (result.size() == 0) {
            dolbomClass.setDisable(1L);
            dolbomClassRepository.save(dolbomClass);
        }
    }
    public void deleteDolbomClass(DolbomClass dolbomClass){
        List<DolbomClass> result = dolbomClassRepository.findByClassName(dolbomClass.getClass_name(), dolbomClass.getClass_num());
        if((result.size() == 1) && (result.get(0).getDisable() == 1)){
            System.out.println("기존 돌봄학급 비활성화");
            dolbomClassRepository.disableDolbomClass(result.get(0).getId());
        } else if ( (result.size() == 1) && (result.get(0).getDisable() == 0)) {
            System.out.println("이미 비활성화된 돌봄학급입니다");
        } else {
            System.out.println("error");
        }
    }

    public List<DolbomClass> sendDolbomClassList (){
        List<DolbomClass> dolbomClassList = dolbomClassRepository.findActivationDolbomClass();
        return dolbomClassList;
    }

    public void addNewStudent(Student student){
        List<Student> result = studentRepository.findByNameGradeGender(student.getName(), student.getGrade(), student.getGender());
        if((result.size() == 1) && (result.get(0).getDisable() == 0)){
            System.out.println("기존 돌봄학생 활성화");
            studentRepository.activationStudent(result.get(0).getId());
        } else if ( (result.size() == 1) && (result.get(0).getDisable() == 1) ) {
            System.out.println("이미 활성화된 돌봄학생입니다");
        } else if (result.size() == 0) {
            System.out.println("새로운 돌봄학생 추가");
            student.setDisable(1L);
            studentRepository.save(student);
        }
    }

    public void deleteStudent(Student student){
        List<Student> result = studentRepository.findByNameGradeGender(student.getName(), student.getGrade(), student.getGender());
        if((result.size() == 1) && (result.get(0).getDisable() == 1)){
            System.out.println("기존 돌봄학생 비활성화");
            studentRepository.disableStudent(result.get(0).getId());
        } else if ( (result.size() == 1) && (result.get(0).getDisable() == 0) ) {
            System.out.println("이미 비활성화된 돌봄학생입니다");
        } else if (result.size() == 0) {
            System.out.println("error");
        }
    }

    public List<Student> sendStudentList(){
        List<Student> studentList = studentRepository.findActivationStudent();
        return studentList;
    }

    public void addNewTeacher(Teacher teacher){
        List<Teacher> result = teacherRepository.findByNameBirth(teacher.getName(), teacher.getBirth_date());
        if((result.size() == 1) && (result.get(0).getDisable() == 0)){
            System.out.println("기존 돌봄교사 활성화");
            teacherRepository.activationTeacher(result.get(0).getId());
        } else if ( (result.size() == 1) && (result.get(0).getDisable() == 1) ) {
            System.out.println("이미 활성화된 돌봄교사입니다");
        } else if (result.size() == 0) {
            System.out.println("새로운 돌봄교사 추가");
            teacher.setDisable(1L);
            teacherRepository.save(teacher);
        }
    }

    public void deleteTeacher(Teacher teacher){
        List<Teacher> result = teacherRepository.findByNameBirth(teacher.getName(), teacher.getBirth_date());
        if((result.size() == 1) && (result.get(0).getDisable() == 1)){
            System.out.println("기존 돌봄교사 비활성화");
            teacherRepository.disableTeacher(result.get(0).getId());
        } else if ( (result.size() == 1) && (result.get(0).getDisable() == 0) ) {
            System.out.println("이미 비활성화된 돌봄교사입니다");
        } else if (result.size()==0) {
            System.out.println("error");
        }
    }

    public List<Teacher> sendTeacherList(){
        List<Teacher> teacherList = teacherRepository.findActivationTeacher();
        return teacherList;
    }

    public void addNewParent(Parent parent){
        List<Parent> result = parentRepository.findByNameBirth(parent.getName(), parent.getBirth_date());
        if((result.size() == 1) && (result.get(0).getDisable()==0)){
            System.out.println("기존 학부모 활성화");
            parentRepository.activationParent(result.get(0).getId());
        } else if ( (result.size() == 1) && (result.get(0).getDisable() == 1) ) {
            System.out.println("이미 활성화된 학부모입니다");
        } else if (result.size() == 0) {
            System.out.println("새로운 학부모 추가");
            parent.setDisable(1L);
            parentRepository.save(parent);
        }
    }

    public void deleteParent(Parent parent){
        List<Parent> result = parentRepository.findByNameBirth(parent.getName(), parent.getBirth_date());
        if((result.size() == 1) && (result.get(0).getDisable()==1)){
            System.out.println("기존 학부모 비활성화");
            parentRepository.disableParent(result.get(0).getId());
        } else if ( (result.size() == 1) && (result.get(0).getDisable() == 0) ) {
            System.out.println("이미 비활성화된 학부모입니다");
        } else if (result.size() == 0) {
            System.out.println("error");
        }
    }

    public List<Parent> sendParentList(){
        List<Parent> parentList = parentRepository.findActivationParent();
        return parentList;
    }

}
