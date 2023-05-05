package com.example.dolbomi.repository;

import com.example.dolbomi.domain.AfterSchoolClass;

import java.util.List;
import java.util.Optional;

public interface AfterSchoolClassRepository {
    AfterSchoolClass save(AfterSchoolClass afterSchoolClass);
    Optional<AfterSchoolClass> findById(Long id);
    List<AfterSchoolClass> findAll();
}