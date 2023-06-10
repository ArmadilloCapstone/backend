package com.example.dolbomi.domain;

import java.sql.Timestamp;

public class PickupMessage {
    private Long id;
    private Long pickup_man_id;
    private String pickup_man_name;
    private Long student_id;
    private String student_name;
    private Long student_grade;
    private Long student_gender;
    private Long teacher_id;
    private String teacher_name;
    private Timestamp date;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPickup_man_id() {
        return pickup_man_id;
    }

    public void setPickup_man_id(Long pickup_man_id) {
        this.pickup_man_id = pickup_man_id;
    }

    public String getPickup_man_name() {
        return pickup_man_name;
    }

    public void setPickup_man_name(String pickup_man_name) {
        this.pickup_man_name = pickup_man_name;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public Long getStudent_grade() {
        return student_grade;
    }

    public void setStudent_grade(Long student_grade) {
        this.student_grade = student_grade;
    }

    public Long getStudent_gender() {
        return student_gender;
    }

    public void setStudent_gender(Long student_gender) {
        this.student_gender = student_gender;
    }

    public Long getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Long teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }


}
