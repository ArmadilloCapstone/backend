package com.example.dolbomi.repository;

import com.example.dolbomi.domain.StudentState;

import java.util.List;
import java.util.Optional;

public interface StudentStateRepository {
    StudentState save(StudentState studentState);
    Optional<StudentState> findById(Long id);
    List<StudentState> findAll();
}
