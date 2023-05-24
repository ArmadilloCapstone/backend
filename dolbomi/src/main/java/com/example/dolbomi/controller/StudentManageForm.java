package com.example.dolbomi.controller;

import java.sql.Date;

public class StudentManageForm {
    private Long id;
    private String name;
    private Long grade;
    private String phone_num;
    private String gender;
    private String class_name;
    private Date birth_date;
    private Long original_class_num;

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

    public Long getGrade() {
        return grade;
    }

    public void setGrade(Long grade) {
        this.grade = grade;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public Long getOriginal_class_num() {
        return original_class_num;
    }

    public void setOriginal_class_num(Long original_class_num) {
        this.original_class_num = original_class_num;
    }
}
