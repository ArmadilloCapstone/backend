package com.example.dolbomi.domain;

public class Guardian {
    private Long id;
    private String name;
    private Long serial_num;
    private String info;

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

    public Long getSerial_num() {
        return serial_num;
    }

    public void setSerial_num(Long serial_num) {
        this.serial_num = serial_num;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
