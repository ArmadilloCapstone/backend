package com.example.dolbomi.service;

import com.example.dolbomi.controller.PickupListRequestForm;
import com.example.dolbomi.controller.PickupRequestForm;
import com.example.dolbomi.controller.StudentPickupForm;
import com.example.dolbomi.domain.*;
import com.example.dolbomi.repository.*;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class PickupService {
    private final StudentRepository studentRepository;
    private final PickupRepository pickupRepository;
    private final ParentRepository parentRepository;
    private final GuardianRepository guardianRepository;
    private final TeacherRepository teacherRepository;
    private final PickupMessageRepository pickupMessageRepository;

    public PickupService(StudentRepository studentRepository, PickupRepository pickupRepository,
                         ParentRepository parentRepository, GuardianRepository guardianRepository,
                         TeacherRepository teacherRepository, PickupMessageRepository pickupMessageRepository){
        this.studentRepository = studentRepository;
        this.pickupRepository = pickupRepository;
        this.parentRepository = parentRepository;
        this.guardianRepository = guardianRepository;
        this.teacherRepository = teacherRepository;
        this.pickupMessageRepository = pickupMessageRepository;
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
            pickupRepository.saveByParent(pickupRequestForm, student.get().getClass_id());
        } else{
            System.out.println("돌봄학생 정보가 없습니다");
            return null;
        }
        return "success";
    }

    public String requestPickupByGuardian(PickupListRequestForm pickupListRequestForm){
        int studentCount = pickupListRequestForm.getStudentPickupFormList().size();
        for(int i = 0; i<studentCount;i++){
            PickupRequestForm pickupRequestForm = new PickupRequestForm();
            Optional<Guardian> guardian = guardianRepository.findBySerialNum(pickupListRequestForm.getPickupManId());
            System.out.println(guardian.get().getName());
            if(guardian.isPresent()){
                pickupRequestForm.setPickupManId(guardian.get().getId());
                pickupRequestForm.setPickupManName(guardian.get().getName());
            } else{
                System.out.println("There is no information about guardian");
                return null;
            }
            Optional<Student> student = studentRepository.findById(pickupListRequestForm.getStudentPickupFormList().get(i).getId());
            if(student.isPresent()){
                pickupRequestForm.setStudentId(student.get().getId());
                pickupRequestForm.setStudentName(student.get().getName());
                pickupRequestForm.setStudentGrade(student.get().getGrade());
                pickupRequestForm.setStudentGender(student.get().getGender());
                pickupRepository.saveByGuardian(pickupRequestForm, student.get().getClass_id());
            } else{
                System.out.println("돌봄학생 정보가 없습니다");
                return null;
            }
        }
        return "success";
    }

    public List<PickupRequestForm> sendPickupForTeacher(Long teacher_id){
        Optional<Teacher> teacher = teacherRepository.findById(teacher_id);
        if(teacher.isPresent()){
            List<PickupRequestForm> pickupRequestFormList = pickupRepository.findAll(teacher.get().getClass_id());
            savePickupLog(teacher, pickupRequestFormList);
            pickupRepository.clearPickupStore(teacher.get().getClass_id());
            return pickupRequestFormList;
        } else {
            System.out.println("유효하지 않은 돌봄교사 id입니다");
            return null;
        }
    }

    private void savePickupLog(Optional<Teacher> teacher, List<PickupRequestForm> pickupRequestFormList) {
        int count = pickupRequestFormList.size();
        for(int i = 0; i< count; i++){
            PickupMessage pickupMessage = new PickupMessage();
            pickupMessage.setPickup_man_id(pickupRequestFormList.get(i).getPickupManId());
            pickupMessage.setPickup_man_name(pickupRequestFormList.get(i).getPickupManName());
            pickupMessage.setStudent_id(pickupRequestFormList.get(i).getStudentId());
            pickupMessage.setStudent_name(pickupRequestFormList.get(i).getStudentName());
            pickupMessage.setStudent_grade(pickupRequestFormList.get(i).getStudentGrade());
            pickupMessage.setStudent_gender(pickupRequestFormList.get(i).getStudentGender());
            pickupMessage.setTeacher_id(teacher.get().getId());
            pickupMessage.setTeacher_name(teacher.get().getName());
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            pickupMessage.setDate(timestamp);
            pickupMessageRepository.save(pickupMessage);
        }
    }

    public void exportPickupLog(Long teacher_id, HttpServletResponse response){
        pickupMessageRepository.exportPickupLog(teacher_id, response);
    }

}
