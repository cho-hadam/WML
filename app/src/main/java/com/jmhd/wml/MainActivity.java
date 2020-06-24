package com.jmhd.wml;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {
    private Button btn_diary;
    private MutableLiveData<ArrayList<Object>> mCalendarList = new MutableLiveData<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_diary = (Button) findViewById(R.id.btn_diary);
        btn_diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                overridePendingTransition(0,0);
                MainActivity.this.finish();
            }
        });

    }

    public void setCalendarList() {
        GregorianCalendar cal = new GregorianCalendar(); // 오늘 날짜

        ArrayList<Object> calendarList = new ArrayList<>();

        for (int i = -300; i < 300; i++) {
            try {
                GregorianCalendar calendar = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + i, 1, 0, 0, 0);

                calendarList.add(calendar.getTimeInMillis()); // 날짜 타입

                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}