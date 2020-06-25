package com.jmhd.wml;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class DiaryActivity extends AppCompatActivity {
    private ImageButton btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        btn_back = (ImageButton)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(backDiary);
    }

    Button.OnClickListener backDiary = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            DiaryActivity.this.finish();
        }
    };
}
