package com.example.dolbomi.repository;

import com.example.dolbomi.domain.StudentSchedule;

import java.util.List;
import java.util.Optional;

public interface StudentScheduleRepository {
    StudentSchedule save(StudentSchedule studentSchedule);
    Optional<StudentSchedule> findById(Long id);
    List<StudentSchedule> findAll();
}
