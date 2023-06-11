package com.example.dolbomi.form;

public class LoginForm {
    private String user_id;
    private String user_pw;
    private Long serial_num;
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

    public Long getOption() {
        return option;
    }

    public Long getSerial_num() {
        return serial_num;
    }

    public void setSerial_num(Long serial_num) {
        this.serial_num = serial_num;
    }

    public void setOption(Long option) {
        this.option = option;
    }
}
