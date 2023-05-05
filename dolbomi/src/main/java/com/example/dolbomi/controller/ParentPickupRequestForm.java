package com.example.dolbomi.controller;

import java.util.Date;

public class ParentPickupRequestForm {
    private String parentName;
    private String studentName;
    private Long studentGrade;
    private Long studentGender;

    public String getParentName() { return parentName; }
    public void setParentName(String parentName) { this.parentName = parentName; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public Long getStudentGrade() { return studentGrade; }
    public void setStudentGrade(Long studentGrade) { this.studentGrade = studentGrade; }
    public Long getStudentGender() { return studentGender; }
    public void setStudentGender(Long studentGender) { this.studentGender = studentGender; }
}
