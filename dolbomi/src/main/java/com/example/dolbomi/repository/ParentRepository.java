package com.example.dolbomi.repository;

import com.example.dolbomi.domain.Parent;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ParentRepository {
    Parent save(Parent parent);
    Optional<Parent> findById(Long id);
    Optional<Parent> findByChildId(Long child_id);
    List<Parent> findByNameBirth(String name, Date birth_date);
    boolean activationParent(Long id);
    boolean disableParent(Long id);
    List<Parent> findActivationParent();
    List<Parent> findAll();
}
