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

    public List<Student> studentFindAllByTid(Long teacher_id) {
        return studentRepository.findAllByTid(teacher_id);
    }
    public List<StudentTime> studentTimeFindAllByTid(Long teacher_id){
        return studentTimeRepository.findAllByTid(teacher_id);
    }
    public List<StudentSchedule> studentScheduleFindAllByTid(Long teacher_id){
        return studentScheduleRepository.findAllByTid(teacher_id);
    }
    public List<AfterSchoolClass> afterSchoolClassFindAll(){
        return afterSchoolClassRepository.findAll();
    }

}
