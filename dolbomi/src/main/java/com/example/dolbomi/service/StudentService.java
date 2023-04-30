package com.example.dolbomi.service;


import com.example.dolbomi.domain.Student;
import com.example.dolbomi.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Long join(Student student) {
        studentRepository.save(student);
        return student.getId();
    }

    public List<Student> findMembers() {
        return studentRepository.findAll();
    }

    public Optional<Student> findOne(Long memberId) {
        return studentRepository.findById(memberId);
    }
}
