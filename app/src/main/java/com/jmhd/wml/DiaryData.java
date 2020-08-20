package com.jmhd.wml;

import java.sql.Blob;
import java.sql.Date;

public class DiaryData {
    private Date date;
    private String title;
    private String content;
    private Blob photo;

    public DiaryData() { }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Blob getPhoto() {
        return photo;
    }

    public void setPhoto(Blob photo) {
        this.photo = photo;
    }
}
