package com.jmhd.wml;

import android.graphics.Bitmap;

public class DiaryData {
    private String date;
    private String title;
    private String content;
    private Bitmap[] photo;

    public DiaryData() { }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public Bitmap[] getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap[] photo) {
        this.photo = photo;
    }
}
