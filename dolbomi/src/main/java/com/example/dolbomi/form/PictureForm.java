package com.example.dolbomi.form;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class PictureForm {
    private String title;
    private String text;
    private List<MultipartFile> file;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<MultipartFile> getFile() {
        return file;
    }

    public void setFile(List<MultipartFile> file) {
        this.file = file;
    }
}
