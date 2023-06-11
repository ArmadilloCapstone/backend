package com.example.dolbomi.form;

public class PickupRequestForm {
    private Long pickupManId;
    private String pickupManName;
    private Long studentId;
    private String studentName;
    private Long studentGrade;
    private Long studentGender;

    public Long getPickupManId() { return pickupManId; }
    public void setPickupManId(Long pickupManId) { this.pickupManId = pickupManId; }
    public String getPickupManName() { return pickupManName; }
    public void setPickupManName(String pickupManName) { this.pickupManName = pickupManName; }
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public Long getStudentGrade() { return studentGrade; }
    public void setStudentGrade(Long studentGrade) { this.studentGrade = studentGrade; }
    public Long getStudentGender() { return studentGender; }
    public void setStudentGender(Long studentGender) { this.studentGender = studentGender; }
}
