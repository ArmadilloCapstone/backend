package com.example.dolbomi;

import com.example.dolbomi.repository.*;
import com.example.dolbomi.service.PickupService;
import com.example.dolbomi.service.StudentService;
import com.example.dolbomi.service.StudentStateService;
import com.example.dolbomi.service.TimelineService;
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
    public PickupService pickupService() {
        return new PickupService(studentRepository(), pickupRepository());
    }
    @Bean
    public StudentStateService studentStateService(){
        return new StudentStateService(studentStateRepository());
    }
    @Bean
    public TimelineService timelineService(){
        return new TimelineService(studentRepository(), studentScheduleRepository(), afterSchoolClassRepository(), studentTimeRepository());
    }
    @Bean
    public AfterSchoolClassRepository afterSchoolClassRepository() {
        return new JdbcTemplateAfterSchoolClasstRepository(dataSource);
    }
    @Bean
    public StudentRepository studentRepository() {
        return new JdbcTemplateStudentRepository(dataSource);
    }
    @Bean
    public StudentScheduleRepository studentScheduleRepository() {
        return new JdbcTemplateStudentScheduleRepository(dataSource);
    }
    @Bean
    public StudentStateRepository studentStateRepository() {
        return new JdbcTemplateStudentStateRepository(dataSource);
    }
    @Bean
    public StudentTimeRepository studentTimeRepository() {
        return new JdbcTemplateStudentTimeRepository(dataSource);
    }

    @Bean
    public GuardianRepository guardianRepository() {
        return new JdbcTemplateGuardianRepository(dataSource);
    }
    @Bean
    public PickupRepository pickupRepository(){
        return new MemoryPickupRepository();
    }
}
