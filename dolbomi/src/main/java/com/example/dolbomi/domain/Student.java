package com.example.dolbomi.domain;

import java.sql.Date;

public class Student {

    private Long id;
    private String name;
    private Long grade;
    private String phone_num;
    private Long gender;
    private Long class_id;
    private Date birth_date;
    private Long disable;
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

    public Long getGender() {
        return gender;
    }

    public void setGender(Long gender) {
        this.gender = gender;
    }

    public Long getClass_id() {
        return class_id;
    }

    public void setClass_id(Long class_id) {
        this.class_id = class_id;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public Long getDisable() {
        return disable;
    }

    public void setDisable(Long disable) {
        this.disable = disable;
    }
    public Long getOriginal_class_num() {
        return original_class_num;
    }
    public void setOriginal_class_num(Long original_class_num) {
        this.original_class_num = original_class_num;
    }
}
