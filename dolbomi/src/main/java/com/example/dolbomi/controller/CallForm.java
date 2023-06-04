package com.example.dolbomi.controller;

import com.example.dolbomi.domain.Teacher;

import java.util.List;

public class CallForm {

    private List<Teacher> teacherList;
    private String school_phone_num;
    private String office_phone_num;

    public List<Teacher> getTeacherList() {
        return teacherList;
    }
    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }
    public String getSchool_phone_num() {
        return school_phone_num;
    }

    public void setSchool_phone_num(String school_phone_num) {
        this.school_phone_num = school_phone_num;
    }

    public String getOffice_phone_num() {
        return office_phone_num;
    }

    public void setOffice_phone_num(String office_phone_num) {
        this.office_phone_num = office_phone_num;
    }
}
