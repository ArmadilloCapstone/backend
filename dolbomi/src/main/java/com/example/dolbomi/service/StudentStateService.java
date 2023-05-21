package com.example.dolbomi.service;

import com.example.dolbomi.controller.StudentStateForm;
import com.example.dolbomi.domain.Parent;
import com.example.dolbomi.domain.Student;
import com.example.dolbomi.domain.StudentState;
import com.example.dolbomi.repository.ParentRepository;
import com.example.dolbomi.repository.StudentRepository;
import com.example.dolbomi.repository.StudentStateRepository;

import java.util.List;
import java.util.Optional;

public class StudentStateService {
    private final StudentStateRepository studentStateRepository;
    private final ParentRepository parentRepository;
    private final StudentRepository studentRepository;

    public StudentStateService(StudentStateRepository studentStateRepository, ParentRepository parentRepository,
                               StudentRepository studentRepository){
        this.studentStateRepository = studentStateRepository;
        this.parentRepository = parentRepository;
        this.studentRepository = studentRepository;

    }

    public String changeState(Long student_id, Long state){
        if(studentStateRepository.changeState(student_id, state)) {
            return "변경완료";
        }
        else return "error";
    }

    public StudentStateForm sendStudentState(Long parent_id){
        Optional<Parent> parent = parentRepository.findById(parent_id);
        if(parent.isPresent()){
            Optional<Student> student = studentRepository.findById(parent.get().getChild_id());
            if(student.isPresent()){
                List<StudentState> result = studentStateRepository.findByStudentId(student.get().getId());
                if(result.size()==1){
                    StudentStateForm studentStateForm = new StudentStateForm();
                    studentStateForm.setId(result.get(0).getId());
                    studentStateForm.setStudent_id(result.get(0).getStudent_id());
                    studentStateForm.setName(student.get().getName());
                    studentStateForm.setState(result.get(0).getState());
                    return studentStateForm;
                } else if (result.size() == 0) {
                    System.out.println("존재하지 않는 돌봄학생입니다");
                    return null;
                } else{
                    System.out.println("돌봄학생id가 중복되어있습니다");
                    return null;
                }
            } else {
                System.out.println("존재하지 않는 돌봄학생입니다");
                return null;
            }
        }else{
            System.out.println("존재하지 않는 학부모입니다");
            return null;
        }
    }

    public List<StudentStateForm> findMembers() {
        return studentStateRepository.findAll();
    }
}
