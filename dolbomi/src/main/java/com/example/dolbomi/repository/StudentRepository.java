package com.example.dolbomi.repository;

        import com.example.dolbomi.domain.Student;

        import java.util.List;
        import java.util.Optional;

public interface StudentRepository {
    Student save(Student student);
    Optional<Student> findById(Long id);
    Student findStudent_idByParentId(Long id);
    List<Student> findByNameGradeGender(String name, Long grade, Long gender);
    boolean activationStudent(Long id);
    boolean disableStudent(Long id);
    List<Student> findStudent_idById(Long id);
    List<Student> findActivationStudent();
    List<Student> findAll();
}
