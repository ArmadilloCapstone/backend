package com.example.dolbomi.controller;

import com.example.dolbomi.domain.AfterSchoolClass;
import com.example.dolbomi.domain.Student;
import com.example.dolbomi.domain.StudentSchedule;
import com.example.dolbomi.domain.StudentTime;
import com.example.dolbomi.service.StudentService;
import com.example.dolbomi.service.TimelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class TimelineController {
    private final TimelineService timelineService;

    @Autowired
    public TimelineController(TimelineService timelineService){
        this.timelineService = timelineService;
    }

    //private final StudentService studentService;

    @PostMapping("/studentFindAll")
    public List<Student> studentFindAll() {
        return timelineService.studentFindAll();
    }

    @PostMapping("/studentTimeFindAll")
    public List<StudentTime> studentTimeFindAll() {
        return timelineService.studentTimeFindAll();
    }

    @PostMapping("/studentScheduleFindAll")
    public List<StudentSchedule> studentScheduleFindAll() {
        return timelineService.studentScheduleFindAll();
    }

    @PostMapping("/AfterSchoolClassFindAll")
    public List<AfterSchoolClass> AfterSchoolClassFindAll() {
        return timelineService.afterSchoolClassFindAll();
    }

}
