package com.example.dolbomi.controller;

public class ChangePwForm {
    private String user_id;
    private String user_pw;
    private String user_new_pw;
    private Long option;

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

    public String getUser_new_pw() {
        return user_new_pw;
    }

    public void setUser_new_pw(String user_new_pw) {
        this.user_new_pw = user_new_pw;
    }

    public Long getOption() {
        return option;
    }

    public void setOption(Long option) {
        this.option = option;
    }
}
