package com.example.dolbomi.repository;

import com.example.dolbomi.domain.Parent;

import java.util.List;
import java.util.Optional;

public interface ParentRepository {
    Parent save(Parent parent);
    Optional<Parent> findById(Long id);
    Optional<Parent> findByChildId(Long child_id);
    List<Parent> findAll();
}
