package com.example.dolbomi.repository;

import com.example.dolbomi.domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    Student save(Student student);
    Optional<Student> findById(Long id);
    List<Student> findAll();
}
