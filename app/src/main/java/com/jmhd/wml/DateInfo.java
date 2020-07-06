package com.jmhd.wml;

import java.io.Serializable;
import java.util.Calendar;

public class DateInfo implements Serializable {
    private String year;
    private String month;
    private String date;

    public DateInfo(String year, String month) {
        this.year = year;
        setMonth(month);
    }

    public String getNow() {
        Calendar calendar = Calendar.getInstance();
        String nowDate = String.valueOf(calendar.get(Calendar.DATE));
        if (nowDate.length() == 1){ // 월이 한 자릿수일 때
            // 앞에 0 추가
            nowDate = "0" + nowDate;
        }
        return year+month+nowDate;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;

        if (month.length() == 1){ // 월이 한 자릿수일 때
            // 앞에 0 추가
            this.month = "0" + month;
        }
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;

        if (date.length() == 1){ // 월이 한 자릿수일 때
            // 앞에 0 추가
            this.date = "0" + date;
        }

    }

    @Override
    public String toString() {
        return year + month + date;
    }
}
