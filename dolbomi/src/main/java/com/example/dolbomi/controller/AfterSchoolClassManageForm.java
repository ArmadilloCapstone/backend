package com.example.dolbomi.controller;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;

public class AfterSchoolClassManageForm {
    private Long id;
    private String class_name;
    @DateTimeFormat(pattern = "kk:mm")
    private Time start_time;
    @DateTimeFormat(pattern = "kk:mm")
    private Time end_time;
    private String day;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public Time getStart_time() {
        return start_time;
    }

    public void setStart_time(Time start_time) {
        this.start_time = start_time;
    }

    public Time getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Time end_time) {
        this.end_time = end_time;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
