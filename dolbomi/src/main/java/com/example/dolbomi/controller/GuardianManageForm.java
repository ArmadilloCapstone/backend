package com.example.dolbomi.controller;

import com.example.dolbomi.domain.Student;

import java.util.List;

public class GuardianManageForm {
    private Long id;
    private String name;
    private Long serial_num;
    private String info;
    private List<Student> studentList;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSerial_num() {
        return serial_num;
    }

    public void setSerial_num(Long serial_num) {
        this.serial_num = serial_num;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
