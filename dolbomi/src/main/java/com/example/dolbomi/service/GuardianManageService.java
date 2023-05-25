package com.example.dolbomi.service;

import com.example.dolbomi.controller.GuardianManageForm;
import com.example.dolbomi.domain.Guardian;
import com.example.dolbomi.domain.Student;
import com.example.dolbomi.domain.StudentOfGuardian;
import com.example.dolbomi.repository.GuardianRepository;
import com.example.dolbomi.repository.StudentOfGuardianRepository;
import com.example.dolbomi.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GuardianManageService {

    private final GuardianRepository guardianRepository;
    private final StudentRepository studentRepository;
    private final StudentOfGuardianRepository studentOfGuardianRepository;
    public GuardianManageService(GuardianRepository guardianRepository, StudentRepository studentRepository,
                                 StudentOfGuardianRepository studentOfGuardianRepository) {
        this.guardianRepository = guardianRepository;
        this.studentRepository = studentRepository;
        this.studentOfGuardianRepository = studentOfGuardianRepository;
    }
    public String addNewGuardian(Guardian guardian){
        List<Guardian> result = guardianRepository.findBySerialNum(guardian.getSerial_num());
        if(result.size()==1){
            System.out.println("This guardian is already exist");
            return "이미 등록된 보호자입니다";
        } else if (result.size()==0) {
            System.out.println("Add new guardian");
            guardianRepository.save(guardian);
            return "success";
        } else {
            return "fail";
        }
    }

    public void deleteGuardian(Long id){
        Optional<Guardian> result = guardianRepository.findById(id);
        if(result.isPresent()){
            System.out.println("Delete guardian");
            List<StudentOfGuardian> studentOfGuardianList = studentOfGuardianRepository.findByGuardian_id(id);
            int count = studentOfGuardianList.size();
            for(int i = 0; i<count;i++){
                 studentOfGuardianRepository.deleteStudentOfGuardian(studentOfGuardianList.get(i).getId());
            }
            guardianRepository.deleteGuardian(id);
        } else{
            System.out.println("Guardian cannot be deleted because it does not exist");
        }
    }
    public List<Student> sendStudentList(Long guardian_id){
        List<Student> result = studentRepository.findActivationStudent();
        int count = result.size();
        List<Student> studentList = new ArrayList<>();
        for(int i = 0; i < count; i++){
            if(studentOfGuardianRepository.findByGuardian_idStudent_id(guardian_id, result.get(i).getId()).size()==1){
                System.out.println("This guardian already has this student");
            } else if (studentOfGuardianRepository.findByGuardian_idStudent_id(guardian_id, result.get(i).getId()).size()==0) {
                studentList.add(result.get(i));
            } else{
                System.out.println("Duplicate Student_of_guardian");
            }
        }
        return studentList;
    }

    public String addNewStudentUnderGuardian(GuardianManageForm guardianManageForm){
        int count = guardianManageForm.getStudentList().size();
        for(int i = 0; i < count; i++){
            StudentOfGuardian studentOfGuardian = new StudentOfGuardian();
            studentOfGuardian.setGuardian_id(guardianManageForm.getId());
            studentOfGuardian.setStudent_id(guardianManageForm.getStudentList().get(i).getId());
            studentOfGuardianRepository.save(studentOfGuardian);
        }
        return "success";
    }

    public void deleteStudentUnderGuardian(Long guardian_id, Long student_id){
        List<StudentOfGuardian> result = studentOfGuardianRepository.findByGuardian_idStudent_id(guardian_id, student_id);
        if(result.size()==1){
            System.out.println("Delete student under guardian");
            studentOfGuardianRepository.deleteStudentOfGuardian(result.get(0).getId());
        } else if (result.size()==0) {
            System.out.println("Student under guardian cannot be deleted because it does not exist");
        } else {
            System.out.println("Duplicate student_of_guardian");
        }
    }

    public List<GuardianManageForm> sendGuardianStudentList() {
        List<StudentOfGuardian> studentOfGuardianList = studentOfGuardianRepository.findAll();
        int count = studentOfGuardianList.size();
        int guardianNum = 0;
        Long[] idList = new Long[count];

        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                if ((idList[j] == 0L) && (idList[j] != studentOfGuardianList.get(i).getGuardian_id())) {
                    idList[j] = studentOfGuardianList.get(i).getGuardian_id();
                    guardianNum++;
                    break;
                } else if ((idList[j] != 0L) && (idList[j] == studentOfGuardianList.get(i).getGuardian_id())) {
                    break;
                }
            }
        }

        List<GuardianManageForm> guardianManageFormList = new ArrayList<>();
        for (int i = 0; i < guardianNum; i++) {
            GuardianManageForm guardianManageForm = new GuardianManageForm();
            guardianManageForm.setId(idList[i]);
            guardianManageForm.setName(guardianRepository.findById(idList[i]).get().getName());
            guardianManageForm.setSerial_num(guardianRepository.findById(idList[i]).get().getSerial_num());
            guardianManageForm.setInfo(guardianRepository.findById(idList[i]).get().getInfo());
            List<Student> studentList = new ArrayList<>();
            for (int j = 0; j < count; j++) {
                if (idList[i] == studentOfGuardianList.get(j).getGuardian_id()) {
                    Student student = new Student();
                    student.setId(studentOfGuardianList.get(j).getStudent_id());
                    student.setName(studentRepository.findById(studentOfGuardianList.get(j).getStudent_id()).get().getName());
                    studentList.add(student);
                }
            }
            guardianManageForm.setStudentList(studentList);
            guardianManageFormList.add(guardianManageForm);
        }
        return guardianManageFormList;
    }
}
