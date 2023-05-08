package com.example.dolbomi.controller;

import com.example.dolbomi.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;

public class TimelineController {

    private final StudentService studentService;

    @GetMapping("/timeline")
    public String foo() {
        return "absolute";
    }
}
