package com.example.dolbomi;

import com.example.dolbomi.repository.JdbcTemplateStudentRepository;
import com.example.dolbomi.repository.StudentRepository;
import com.example.dolbomi.service.StudentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Bean
    public StudentService studentService(){
        return new StudentService(studentRepository());
    }
    @Bean
    public StudentRepository studentRepository() {
        return new JdbcTemplateStudentRepository(dataSource);
    }
}
