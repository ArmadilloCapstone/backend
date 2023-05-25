package com.example.dolbomi.domain;

public class StudentOfGuardian {
    private Long id;
    private Long guardian_id;
    private Long student_id;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGuardian_id() {
        return guardian_id;
    }

    public void setGuardian_id(Long guardian_id) {
        this.guardian_id = guardian_id;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

}
