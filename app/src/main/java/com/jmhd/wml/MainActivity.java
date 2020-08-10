package com.jmhd.wml;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.Serializable;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private final int NEXT_BUTTON = 1;
    private final int BACK_BUTTON = -1;
    private Button btn_diary;
    private Button btn_view;
    private Button btn_write;
    private TextView text_header;
    private ImageButton btn_back;
    private ImageButton btn_next;
    private int year;
    private int get_month;
    private GridView view_calendar;
    private CalendarAdapter calendarAdapter;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 변수들 값 가져오기
        defineVar();

        // nav button onClick
        btn_diary.setOnClickListener(goSub);

        // ~ my life button
        btn_view.setOnClickListener(goDiary);
        btn_write.setOnClickListener(goWrite);

        // calender 년월 setText
        setHeader(0);
        // calendar header arrow button onClick
        btn_back.setOnClickListener(backMonth);
        btn_next.setOnClickListener(nextMonth);

        // calendar grid view adapter 설정
        setCalendarAdapter();
    }

    protected void defineVar() {
        // 캘린더 인스턴스 생성, 년도, 0~11 사이의 월 구하기 (month의 인덱스)
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        get_month = calendar.get(Calendar.MONTH);

        // nav button 가져오기
        btn_diary = (Button) findViewById(R.id.btn_diary);

        // ~ my life button 가져오기
        btn_view = (Button) findViewById(R.id.btn_view);
        btn_write = (Button) findViewById(R.id.btn_write);

        // calendar header 가져오기 (양 옆 화살표 버튼, 년월 텍스트)
        text_header = (TextView) findViewById(R.id.text_header);
        btn_back = (ImageButton) findViewById(R.id.btn_back);
        btn_next = (ImageButton) findViewById(R.id.btn_next);

        // calendar grid view 가져오기
        view_calendar = (GridView) findViewById(R.id.view_calendar);
    }

    // nav button onClick
    Button.OnClickListener goSub = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, SubActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            overridePendingTransition(0,0);
            MainActivity.this.finish();
        }
    };

    // ~ my life button onClick
    Button.OnClickListener goDiary = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, DiaryActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

            // DateInfo 클래스 넘김
            DateInfo dateInfo = calendarAdapter.getDateinfo();
            intent.putExtra("dateInfo", dateInfo);

            startActivity(intent);
            overridePendingTransition(0,0);
        }
    };
    Button.OnClickListener goWrite = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, WriteActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

            // DateInfo 클래스 넘김
            DateInfo dateInfo = calendarAdapter.getDateinfo();
            intent.putExtra("dateInfo", dateInfo);

            startActivity(intent);
            overridePendingTransition(0,0);
        }
    };

    // calendar header arrow button onClick
    Button.OnClickListener backMonth = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setHeader(BACK_BUTTON);
            setCalendarAdapter();
        }
    };
    Button.OnClickListener nextMonth = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setHeader(NEXT_BUTTON);
            setCalendarAdapter();
        }
    };

    // GridView에 Adapter 연결
   private void setCalendarAdapter() {
        calendarAdapter = new CalendarAdapter(MainActivity.this, year, get_month);
        view_calendar.setAdapter(calendarAdapter);
        calendarAdapter.setDate();
    }

    // calendar header 가져오기 (양 옆 화살표 버튼, 년월 텍스트)
    protected void setHeader(int click) { // back or next click flag
        // 구한 month(0~11)에 대한 영어 version
        String[] month_en = {
                "Junuary",
                "February",
                "March",
                "April",
                "May",
                "June",
                "July",
                "August",
                "September",
                "October",
                "November",
                "December"
        };

        if (click == NEXT_BUTTON) { // next button click
            if (get_month != 11) { // 12월이 아닐 경우
                get_month += 1; // 월 인덱스 증가
            } else { // 12월일 경우
                year += 1; // 년도 증가
                get_month = 0; // 월 초기화 (1월)
            }
        } else if (click == BACK_BUTTON) { // back button click
            if (get_month != 0) { // 1월이 아닐 경우
                get_month -= 1; // 월 인덱스 감소
            } else { // 1월일 경우
                year -= 1; // 년도 감소
                get_month = 11; // 월 초기화 (12월)
            }
        }

        // 년월 TextView에 setText
        text_header.setText(month_en[get_month] + " " + String.valueOf(year));
    }
}