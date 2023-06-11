package com.example.dolbomi.form;

import java.util.List;

public class PickupListRequestForm {
    private Long pickupManId;
    private String pickupManName;
    private List<StudentPickupForm> studentPickupFormList;
    public Long getPickupManId() {
        return pickupManId;
    }

    public void setPickupManId(Long pickupManId) {
        this.pickupManId = pickupManId;
    }
    public String getPickupManName() {
        return pickupManName;
    }

    public void setPickupManName(String pickupManName) {
        this.pickupManName = pickupManName;
    }

    public List<StudentPickupForm> getStudentPickupFormList() {
        return studentPickupFormList;
    }

    public void setStudentPickupFormList(List<StudentPickupForm> studentPickupFormList) {
        this.studentPickupFormList = studentPickupFormList;
    }
}
