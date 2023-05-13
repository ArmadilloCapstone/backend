package com.example.dolbomi.service;

import com.example.dolbomi.controller.StudentManageForm;
import com.example.dolbomi.domain.AdminAccount;
import com.example.dolbomi.domain.DolbomClass;
import com.example.dolbomi.domain.Parent;
import com.example.dolbomi.domain.Student;
import com.example.dolbomi.repository.AdminAccountRepository;
import com.example.dolbomi.repository.DolbomClassRepository;
import com.example.dolbomi.repository.ParentRepository;
import com.example.dolbomi.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdminService {
    private final DolbomClassRepository dolbomClassRepository;
    private final StudentRepository studentRepository;
    private final ParentRepository parentRepository;
    private final AdminAccountRepository adminAccountRepository;

    public AdminService(DolbomClassRepository dolbomClassRepository, StudentRepository studentRepository,
                        ParentRepository parentRepository, AdminAccountRepository adminAccountRepository) {
        this.dolbomClassRepository = dolbomClassRepository;
        this.studentRepository = studentRepository;
        this.parentRepository = parentRepository;
        this.adminAccountRepository = adminAccountRepository;
    }

    public List<StudentManageForm> searchAllStudent(AdminAccount adminAccount){
        boolean validationCheck = isValidationCheck(adminAccount);
        if(validationCheck == false){
            System.out.println("등록되지 않은 사용자의 요청");
            return null;
        }
        List<StudentManageForm> studentManageFormList = new ArrayList<>();
        List<Student> studentList = studentRepository.findAll();
        int studentCount = studentList.size();
        for(int i = 0; i < studentCount; i++){
            StudentManageForm studentManageForm = new StudentManageForm();
            studentManageForm.setStudentName(studentList.get(i).getName());
            studentManageForm.setStudentGender(studentList.get(i).getGender());
            studentManageForm.setStudentGrade(studentList.get(i).getGrade());
            studentManageForm.setOriginalClassNum(studentList.get(i).getOriginal_class_num());
            dolbomClassSetting(studentList, i, studentManageForm);
            parentSetting(studentList, i, studentManageForm);
            studentManageFormList.add(studentManageForm);
        }
        return studentManageFormList;
    }

    private boolean isValidationCheck(AdminAccount adminAccount) {
        List<AdminAccount> adminAccountList = adminAccountRepository.findAll();
        int adminCount = adminAccountList.size();
        boolean validationCheck = false;
        for(int i = 0; i < adminCount; i++){
            if(isRegisterdAdmin(adminAccount, adminAccountList, i)){
                validationCheck = true;
                break;
            }
        }
        return validationCheck;
    }

    private static boolean isRegisterdAdmin(AdminAccount adminAccount, List<AdminAccount> adminAccountList, int i) {
        return (adminAccount.getUser_id().equals(adminAccountList.get(i).getUser_id())) && (adminAccount.getUser_pw().equals(adminAccountList.get(i).getUser_pw()));
    }

    private void parentSetting(List<Student> studentList, int i, StudentManageForm studentManageForm) {
        Optional<Parent> parent = parentRepository.findByChildId(studentList.get(i).getId());
        if(parent.isPresent()){
            studentManageForm.setParentName(parent.get().getName());
            studentManageForm.setParentPhoneNum(parent.get().getPhone_num());
        }
        else{
            studentManageForm.setParentName("존재하지 않는 학부모 이름");
            studentManageForm.setParentPhoneNum("존재하지 않는 학부모 휴대폰번호");
        }
    }

    private void dolbomClassSetting(List<Student> studentList, int i, StudentManageForm studentManageForm) {
        Optional<DolbomClass> dolbomClass = dolbomClassRepository.findById(studentList.get(i).getClass_id());
        if(dolbomClass.isPresent()){
            studentManageForm.setDolbomClassName(dolbomClass.get().getClass_name());
        }
        else{
            studentManageForm.setDolbomClassName("존재하지 않는 돌봄반");
        }
    }

}
