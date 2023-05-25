package com.example.dolbomi.repository;

import com.example.dolbomi.domain.StudentSchedule;

import java.util.List;
import java.util.Optional;

public interface StudentScheduleRepository {
    StudentSchedule save(StudentSchedule studentSchedule);
    Optional<StudentSchedule> findById(Long id);
    List<StudentSchedule> findByStudent_idClass_id(Long student_id, Long class_id);
    List<StudentSchedule> findByClass_id(Long class_id);
    List<StudentSchedule> findByStudent_id(Long student_id);
    boolean deleteStudentSchedule(Long id);
    List<StudentSchedule> findAll();
}
