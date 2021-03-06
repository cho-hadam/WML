package com.jmhd.wml;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class DiaryActivity extends AppCompatActivity {
    private ImageButton btn_back;
    private LinearLayout layout;
    private ImageButton btn_menu;
    private Button btn_edit, btn_delete;
    private DateInfo dateInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        btn_back = (ImageButton)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(backDiary);
        btn_menu = (ImageButton)findViewById(R.id.btn_menu);
        btn_menu.setOnClickListener(menu);
        layout = (LinearLayout)findViewById(R.id.LinearLayout_menu);
        btn_edit = (Button)findViewById(R.id.btn_edit);
        btn_delete = (Button)findViewById(R.id.btn_delete);

        // 누른 날짜 알기 위해서 DateInfo 클래스 받아옴
        Intent intent = getIntent();
        dateInfo = (DateInfo) intent.getSerializableExtra("dateInfo");

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
            if(layout.getVisibility() == view.GONE) {
                layout.setVisibility(view.VISIBLE);
            } else {
                layout.setVisibility(view.GONE);
            }
        }
    };


}
