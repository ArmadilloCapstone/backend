package com.example.dolbomi.form;

import java.sql.Date;

public class ParentManageForm {
    private Long id;
    private String name;
    private String phone_num;
    private String gender;
    private Date birth_date;
    private String child_name;

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
    public Date getBirth_date() {
        return birth_date;
    }
    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }
    public String getChild_name() {
        return child_name;
    }
    public void setChild_name(String child_name) {
        this.child_name = child_name;
    }

}
