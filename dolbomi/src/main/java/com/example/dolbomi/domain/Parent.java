package com.example.dolbomi.domain;

import java.sql.Date;

public class Parent {
    private Long id;
    private String name;
    private String phone_num;
    private Long gender;
    private Date birth_date;
    private Long child_id;
    private Long class_id;
    private Long disable;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhone_num() { return phone_num; }
    public void setPhone_num(String phone_num) { this.phone_num = phone_num; }
    public Long getGender() { return gender; }
    public void setGender(Long gender) { this.gender = gender; }
    public Date getBirth_date() { return birth_date; }
    public void setBirth_date(Date birth_date) { this.birth_date = birth_date; }
    public Long getChild_id() { return child_id; }
    public void setChild_id(Long child_id) { this.child_id = child_id; }
    public Long getClass_id() { return class_id; }
    public void setClass_id(Long class_id) { this.class_id = class_id; }
    public Long getDisable() { return disable; }
    public void setDisable(Long disable) { this.disable = disable; }
}
