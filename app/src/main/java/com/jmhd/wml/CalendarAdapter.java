package com.jmhd.wml;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class CalendarAdapter extends BaseAdapter {
    private ArrayList<Date> dateArrayList = new ArrayList<Date>() ;
    Context context = null;
    TextView date = null;
    private int year;
    private int get_month;

    // Constructor
    public CalendarAdapter(Context context, int year, int get_month) {
        this.context = context;
        this.year = year;
        this.get_month = get_month;
    }

    @Override
    public int getCount() {
        return dateArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return dateArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.calendar_date, parent, false);
        }

        // calendar_date.xml에 있는 TextView
        date = (TextView) convertView.findViewById(R.id.text_date);

        // ArrayList에서 한 개 뽑아옴
        Date dateViewItem = dateArrayList.get(position);

        // onClick event 설정
        date.setOnClickListener(new DateClickListener(context));

        // 날짜 설정
        date.setText(String.valueOf(dateViewItem.getDate()));

        if(dateViewItem.getTextColor().indexOf("#") != -1) { // 실제 날짜면
            // 객체에 설정 되어 있는 색을 그대로 TextView에 설정
            date.setTextColor(Color.parseColor(dateViewItem.getTextColor()));
        } else { // 빈 날짜 표현이면 (0일)
            // TextView에 투명으로 설정
            date.setTextColor(0x000000);
        }

        return convertView;
    }

    // 달의 마지막 날짜를 구하는 메서드
    protected int getMaxDate() {
        Calendar calendar = Calendar.getInstance();
        // MainActivity.java에서 넘어온 year와 month로 set
        calendar.set(year, get_month, 1);
        // set한 달의 마지막 날짜를 구함
        int maxDate = calendar.getActualMaximum(Calendar.DATE);
        return maxDate;
    }

    // 요일에 따른 날짜 색을 구하는 메서드
    protected String getTextColor(int date_count) {
        String[] textColor = { // 일: 빨강, 월~금: 검정, 토: 파랑
                "#FF0000", "#000000", "#000000", "#000000", "#000000", "#000000", "#2400FF"
        };

        Calendar calendar = Calendar.getInstance();
        // date_count는 호출할 때의 날짜
        calendar.set(year, get_month, date_count);
        // 그 날짜의 요일을 구함
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        // 인덱스는 0부터 시작, 일요일은 1로 반환되므로 -1
        return textColor[dayOfWeek-1];
    }

    // 빈 날짜 객체 생성
    public void emptyDate() {
        // 시작하는 요일(1~7)까지 반복
        for (int i = 1; i < getStartDayOfWeek(); i++) {
            Date empty = new Date();
            // 0일로 설정
            empty.setDate(0);
            // 실제로 Color는 getView에서 설정함. 임의로 0
            empty.setTextColor("0");
            // ArrayList에 객체 추가
            dateArrayList.add(empty);
        }
    }

    // 날짜별 객체 생성
    public void setDate() {
        // 빈 날짜 생성
        emptyDate();

        // 1일부터 그 달의 마지막 날짜까지 반복
        for (int i = 1; i <= getMaxDate(); i++) {
            Date d = new Date();
            // 1일부터 마지막 날짜까지 증가하는 i
            d.setDate(i);
            d.setTextColor(getTextColor(i));
            // ArrayList에 객체 추가
            dateArrayList.add(d);
        }
    }

    // 그 달의 시작 요일을 알아내는 메서드
    public int getStartDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, get_month, 1);
        int start_dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        return start_dayOfWeek;
}
}
