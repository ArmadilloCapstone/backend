package com.example.dolbomi.domain;

import java.sql.Date;
import java.sql.Timestamp;

public class Message {
    private Long id;
    private String sender_id;
    private String sender_name;
    private String receiver_id;
    private String receiver_name;
    private String text;
    private Timestamp date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }

    public String getSender_name() {
        return sender_name;
    }

    public void setSender_name(String sender_name) {
        this.sender_name = sender_name;
    }

    public String getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(String receiver_id) {
        this.receiver_id = receiver_id;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id + "\"" +
                ",\"sender_id\":\"" + sender_id + "\"" +
                ",\"sender_name\":\"" + sender_name + "\"" +
                ",\"receiver_id\":\"" + receiver_id + "\"" +
                ",\"receiver_name\":\"" + receiver_name + "\"" +
                ",\"text\":\"" + text + "\"" +
                ",\"date\":\"" + date.toString() +
                "\"}";
    }
}
