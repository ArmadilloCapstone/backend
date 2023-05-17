package com.example.dolbomi.repository;

import com.example.dolbomi.domain.StudentTime;

import java.util.List;
import java.util.Optional;

public interface StudentTimeRepository {
    StudentTime save(StudentTime studentTime);
    Optional<StudentTime> findById(Long id);
    List<StudentTime> findByStudent_id(Long student_id);
    boolean deleteStudentTime(Long id);
    List<StudentTime> findAll();
}
