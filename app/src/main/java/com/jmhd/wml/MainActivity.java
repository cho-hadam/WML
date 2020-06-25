package com.jmhd.wml;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private Button btn_diary;
    private TextView text_month;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_diary = (Button) findViewById(R.id.btn_diary);
        btn_diary.setOnClickListener(goSub);

        text_month = (TextView) findViewById(R.id.text_month);
        setDate(text_month);
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

    protected void setDate(TextView text_month) {
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
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int get_month = calendar.get(Calendar.MONTH);
        String month = month_en[get_month];

        text_month.setText(month + " " + String.valueOf(year));
    }
}