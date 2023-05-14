package com.example.dolbomi.service;

import com.example.dolbomi.domain.AfterSchoolClass;
import com.example.dolbomi.domain.Student;
import com.example.dolbomi.domain.StudentSchedule;
import com.example.dolbomi.domain.StudentTime;
import com.example.dolbomi.repository.AfterSchoolClassRepository;
import com.example.dolbomi.repository.StudentRepository;
import com.example.dolbomi.repository.StudentScheduleRepository;
import com.example.dolbomi.repository.StudentTimeRepository;

import java.util.List;

public class TimelineService {
    private final StudentRepository studentRepository;
    private final StudentScheduleRepository studentScheduleRepository;
    private final StudentTimeRepository studentTimeRepository;
    private final AfterSchoolClassRepository afterSchoolClassRepository;

    public TimelineService(StudentRepository studentRepository, StudentScheduleRepository studentScheduleRepository, AfterSchoolClassRepository afterSchoolClassRepository, StudentTimeRepository studentTimeRepository){
        this.studentRepository = studentRepository;
        this.studentScheduleRepository = studentScheduleRepository;
        this.afterSchoolClassRepository = afterSchoolClassRepository;
        this.studentTimeRepository = studentTimeRepository;
    }

    public List<Student> studentFindAll() {
        return studentRepository.findAll();
    }
    public List<StudentTime> studentTimeFindAll(){
        return studentTimeRepository.findAll();
    }
    public List<StudentSchedule> studentScheduleFindAll(){
        return studentScheduleRepository.findAll();
    }
    public List<AfterSchoolClass> afterSchoolClassFindAll(){
        return afterSchoolClassRepository.findAll();
    }

}
