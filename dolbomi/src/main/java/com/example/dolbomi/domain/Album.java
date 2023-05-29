package com.example.dolbomi.domain;

import java.sql.Date;

public class Album {

    private long id;
    private String title;
    private long writer_id;
    private long class_id;
    private Date uploaded_date;
    private String contents;
    private String file_url;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getWriter_id() {
        return writer_id;
    }

    public void setWriter_id(long writer_id) {
        this.writer_id = writer_id;
    }

    public long getClass_id() {
        return class_id;
    }

    public void setClass_id(long class_id) {
        this.class_id = class_id;
    }

    public Date getUploaded_date() {
        return uploaded_date;
    }

    public void setUploaded_date(Date uploaded_date) {
        this.uploaded_date = uploaded_date;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }



}
