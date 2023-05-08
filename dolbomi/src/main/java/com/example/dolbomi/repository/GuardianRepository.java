package com.example.dolbomi.repository;

import com.example.dolbomi.domain.Guardian;
import com.example.dolbomi.domain.Student;

import java.util.List;
import java.util.Optional;

public interface GuardianRepository {
    Guardian save(Guardian guardian);
    Optional<Guardian> findById(Long id);
    List<Guardian> findAll();
}