package com.example.dolbomi.controller;

import com.example.dolbomi.domain.AfterSchoolClass;
import com.example.dolbomi.domain.Student;
import com.example.dolbomi.domain.StudentSchedule;
import com.example.dolbomi.domain.StudentTime;
import com.example.dolbomi.service.StudentService;
import com.example.dolbomi.service.TimelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/studentFindAll/{teacher_id}")
    public List<Student> studentFindAllByTid(@PathVariable("teacher_id") Long teacher_id) {
        return timelineService.studentFindAllByTid(teacher_id);
    }

    @PostMapping("/studentTimeFindAll/{teacher_id}")
    public List<StudentTime> studentTimeFindAllByTid(@PathVariable("teacher_id") Long teacher_id) {
        return timelineService.studentTimeFindAllByTid(teacher_id);
    }

    @PostMapping("/studentScheduleFindAll/{teacher_id}")
    public List<StudentSchedule> studentScheduleFindAllByTid(@PathVariable("teacher_id") Long teacher_id) {
        return timelineService.studentScheduleFindAllByTid(teacher_id);
    }

    @PostMapping("/AfterSchoolClassFindAll")
    public List<AfterSchoolClass> AfterSchoolClassFindAll() {
        return timelineService.afterSchoolClassFindAll();
    }

}
