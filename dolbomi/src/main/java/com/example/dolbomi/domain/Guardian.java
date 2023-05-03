package com.example.dolbomi.domain;

import java.util.Date;

public class Guardian {
    private Long id;
    private String name;
    private Long serial_num;
    private String desc;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Long getSerial_num() { return serial_num; }
    public void setSerial_num(Long serial_num) { this.serial_num = serial_num; }
    public String  getDesc() { return desc; }
    public void setSerial_num(String desc) { this.desc = desc; }
}
