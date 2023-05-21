package com.example.dolbomi.repository;

import com.example.dolbomi.controller.StudentStateForm;
import com.example.dolbomi.domain.StudentState;

import java.util.List;
import java.util.Optional;

public interface StudentStateRepository {
    StudentState save(StudentState studentState);
    Boolean changeState(Long student_id, Long state);
    Optional<StudentState> findById(Long id);
    List<StudentState> findByStudentId(Long student_id);
    List<StudentStateForm> findAll();
}
