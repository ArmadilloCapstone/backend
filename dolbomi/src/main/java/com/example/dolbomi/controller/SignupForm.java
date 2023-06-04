package com.example.dolbomi.controller;

public class SignupForm {
    private Long serical_num;
    private String user_id;
    private String user_pw;

    private String phone_num;
    private String name;
    private String info;
    private Long option;

    public Long getSerical_num() {
        return serical_num;
    }

    public void setSerical_num(Long serical_num) {
        this.serical_num = serical_num;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_pw() {
        return user_pw;
    }

    public void setUser_pw(String user_pw) {
        this.user_pw = user_pw;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOption() {
        return option;
    }

    public void setOption(Long option) {
        this.option = option;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
