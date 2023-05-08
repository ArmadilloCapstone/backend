package com.example.dolbomi.controller;

import com.example.dolbomi.domain.AfterSchoolClass;
import com.example.dolbomi.domain.Student;
import com.example.dolbomi.domain.StudentSchedule;
import com.example.dolbomi.domain.StudentTime;
import com.example.dolbomi.service.StudentService;
import com.example.dolbomi.service.TimelineService;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class TimelineController {
    private final TimelineService timelineService;

    TimelineController(TimelineService timelineService){
        this.timelineService = timelineService;
    }

    //private final StudentService studentService;

    @GetMapping("/studentFindAll")
    public List<Student> studentFindAll() {
        return timelineService.studentFindAll();
    }

    @GetMapping("/studentTimeFindAll")
    public List<StudentTime> studentTimeFindAll() {
        return timelineService.studentTimeFindAll();
    }

    @GetMapping("/studentScheduleFindAll")
    public List<StudentSchedule> studentScheduleFindAll() {
        return timelineService.studentScheduleFindAll();
    }

    @GetMapping("/AfterSchoolClassFindAll")
    public List<AfterSchoolClass> AfterSchoolClassFindAll() {
        return timelineService.afterSchoolClassFindAll();
    }

}
