package com.example.dolbomi.domain;

import java.sql.Time;
import java.util.Date;

public class StudentTime {
    private Long id;
    private Long student_id;
    private Time entry_1;
    private Time entry_2;
    private Time entry_3;
    private Time entry_4;
    private Time entry_5;
    private Time off_1;
    private Time off_2;
    private Time off_3;
    private Time off_4;
    private Time off_5;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public Time getEntry_1() {
        return entry_1;
    }

    public void setEntry_1(Time entry_1) {
        this.entry_1 = entry_1;
    }

    public Time getEntry_2() {
        return entry_2;
    }

    public void setEntry_2(Time entry_2) {
        this.entry_2 = entry_2;
    }

    public Time getEntry_3() {
        return entry_3;
    }

    public void setEntry_3(Time entry_3) {
        this.entry_3 = entry_3;
    }

    public Time getEntry_4() {
        return entry_4;
    }

    public void setEntry_4(Time entry_4) {
        this.entry_4 = entry_4;
    }

    public Time getEntry_5() {
        return entry_5;
    }

    public void setEntry_5(Time entry_5) {
        this.entry_5 = entry_5;
    }

    public Time getOff_1() {
        return off_1;
    }

    public void setOff_1(Time off_1) {
        this.off_1 = off_1;
    }

    public Time getOff_2() {
        return off_2;
    }

    public void setOff_2(Time off_2) {
        this.off_2 = off_2;
    }

    public Time getOff_3() {
        return off_3;
    }

    public void setOff_3(Time off_3) {
        this.off_3 = off_3;
    }

    public Time getOff_4() {
        return off_4;
    }

    public void setOff_4(Time off_4) {
        this.off_4 = off_4;
    }

    public Time getOff_5() {
        return off_5;
    }

    public void setOff_5(Time off_5) {
        this.off_5 = off_5;
    }
}
