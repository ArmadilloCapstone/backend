package com.example.dolbomi.repository;

import com.example.dolbomi.domain.DolbomClass;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface DolbomClassRepository {
    DolbomClass save(DolbomClass dolbomClass);
    Optional<DolbomClass> findById(Long id);
    List<DolbomClass> findByClassNameClassNum(String class_name, Long class_num);
    List<DolbomClass> findByClassName(String class_name);
    List<DolbomClass> findByClassNum(Long class_num);
    boolean activationDolbomClass(Long id);
    boolean disableDolbomClass(Long id);
    boolean updateDolbomClass(Long id, String year_seme);
    List<DolbomClass> findActivationDolbomClass();
    List<DolbomClass> findAll();
}
