package com.example.dolbomi.controller;

public class StudentManageForm {
    private String studentName;
    private Long studentGender;
    private Long studentGrade;
    private Long originalClassNum;
    private String dolbomClassName;
    private String parentName;
    private String parentPhoneNum;

    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public Long getStudentGender() {
        return studentGender;
    }
    public void setStudentGender(Long studentGender) {
        this.studentGender = studentGender;
    }
    public Long getStudentGrade() {
        return studentGrade;
    }
    public void setStudentGrade(Long studentGrade) {
        this.studentGrade = studentGrade;
    }
    public Long getOriginalClassNum() {
        return originalClassNum;
    }
    public void setOriginalClassNum(Long originalClassNum) {
        this.originalClassNum = originalClassNum;}
    public String getDolbomClassName() {
        return dolbomClassName;}
    public void setDolbomClassName(String dolbomClassName) {
        this.dolbomClassName = dolbomClassName;}
    public String getParentName() {
        return parentName;}
    public void setParentName(String parentName) {
        this.parentName = parentName;}
    public String getParentPhoneNum() {
        return parentPhoneNum;}
    public void setParentPhoneNum(String parentPhoneNum) {
        this.parentPhoneNum = parentPhoneNum;}
}
