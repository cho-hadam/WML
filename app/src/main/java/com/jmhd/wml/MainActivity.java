package com.jmhd.wml;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private Button btn_diary;
    private Button btn_view;
    private TextView text_month;
    private ImageButton btn_back;
    private ImageButton btn_next;
    private int year;
    private int get_month;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        get_month = calendar.get(Calendar.MONTH);

        btn_diary = (Button) findViewById(R.id.btn_diary);
        btn_diary.setOnClickListener(goSub);

        btn_view = (Button) findViewById(R.id.btn_view);
        btn_view.setOnClickListener(goDiary);

        text_month = (TextView) findViewById(R.id.text_month);
        setDate(text_month, 0);

        btn_back = (ImageButton) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(backMonth);

        btn_next = (ImageButton) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(nextMonth);
    }

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

    Button.OnClickListener goDiary = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, DiaryActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            overridePendingTransition(0,0);
        }
    };

    Button.OnClickListener backMonth = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setDate(text_month, -1);
        }
    };

    Button.OnClickListener nextMonth = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setDate(text_month, 1);
        }
    };

    protected void setDate(TextView text_month, int click) {
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

        String month = month_en[get_month];

        if(click == 0) {
            text_month.setText(month + " " + String.valueOf(year));

        } else if (click == 1) {
            if (get_month != 11) {
                get_month += 1;
                month = month_en[get_month];
            } else {
                year += 1;
                get_month = 0;
                month = month_en[get_month];
            }
            text_month.setText(month + " " + String.valueOf(year));

        } else {
            if (get_month != 0) {
                get_month -= 1;
                month = month_en[get_month];
            } else {
                year -= 1;
                get_month = 11;
                month = month_en[get_month];
            }
            text_month.setText(month + " " + String.valueOf(year));
        }

    }
}