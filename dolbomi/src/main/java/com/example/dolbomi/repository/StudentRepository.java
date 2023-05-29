package com.example.dolbomi.repository;

        import com.example.dolbomi.domain.Student;

        import java.sql.Date;
        import java.util.List;
        import java.util.Optional;

public interface StudentRepository {
    Student save(Student student);
    Optional<Student> findById(Long id);
    List<Student> findByName(String name);
    Student findStudent_idByParentId(Long id);
    List<Student> findByNameBirth(String name, Date birth_date);
    boolean activationStudent(Long id);
    boolean disableStudent(Long id);
    List<Student> findStudent_idById(Long id);
    List<Student> findActivationStudent();
    List<Student> findAll();
    List<Student> findAllByTid(Long teacher_id);
}
