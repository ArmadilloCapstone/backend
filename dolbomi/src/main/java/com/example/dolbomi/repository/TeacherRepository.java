package com.example.dolbomi.repository;

import com.example.dolbomi.domain.Teacher;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TeacherRepository {
    Teacher save(Teacher teacher);
    Optional<Teacher> findById(Long id);
    List<Teacher> findByNameBirth(String name, Date birth_date);
    List<Teacher> findByClass_id(Long class_id);
    boolean activationTeacher(Long id);
    boolean disableTeacher(Long id);
    List<Teacher> findActivationTeacher();
    List<Teacher> findAll();
}
