package com.example.dolbomi.repository;

import com.example.dolbomi.domain.Student;
import com.example.dolbomi.domain.StudentOfGuardian;

import java.util.List;
import java.util.Optional;

public interface StudentOfGuardianRepository {
    StudentOfGuardian save(StudentOfGuardian studentOfGuardian);
    Optional<StudentOfGuardian> findById(Long id);
    List<StudentOfGuardian> findByGuardian_idStudent_id(Long guardian_id, Long student_id);
    List<StudentOfGuardian> findByGuardian_id(Long guardian_id);
    boolean deleteStudentOfGuardian(Long id);
    List<StudentOfGuardian> findAll();
}
