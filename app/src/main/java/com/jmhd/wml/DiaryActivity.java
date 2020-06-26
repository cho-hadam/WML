package com.jmhd.wml;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class DiaryActivity extends AppCompatActivity {
    private ImageButton btn_back;
    private LinearLayout layout;
    private ImageButton btn_menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        btn_back = (ImageButton)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(backDiary);
        btn_menu = (ImageButton)findViewById(R.id.btn_menu);
        btn_menu.setOnClickListener(menu);
        layout = (LinearLayout)findViewById(R.id.LinearLayout_menu);
        layout.setVisibility(View.GONE);
    }

    Button.OnClickListener backDiary = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            DiaryActivity.this.finish();
        }
    };
    Button.OnClickListener menu = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            layout.setVisibility(View.VISIBLE);
        }
    };


}
