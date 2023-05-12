package com.example.dolbomi.repository;

import com.example.dolbomi.domain.DolbomClass;

import java.util.List;
import java.util.Optional;

public interface DolbomClassRepository {
    DolbomClass save(DolbomClass dolbomClass);
    Optional<DolbomClass> findById(Long id);
    List<DolbomClass> findAll();
}
