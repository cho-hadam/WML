package com.jmhd.wml;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class WriteActivity extends AppCompatActivity {
    private ImageButton btn_save;
    private ImageButton btn_back;
    private EditText input_title;
    private EditText input_content;
    private DateInfo dateInfo;
    private TextView text_write_date;


   // DBHelper db = new DBHelper(getApplicationContext(), "WML.db", null, 1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        // 누른 날짜 알기 위해서 DateInfo 클래스 받아옴
        Intent intent = getIntent();
        dateInfo = (DateInfo) intent.getSerializableExtra("dateInfo");

        text_write_date = (TextView) findViewById(R.id.text_write_date);
        text_write_date.setText(dateInfo.toString());

        btn_save = (ImageButton) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(save);

        btn_back = (ImageButton) findViewById(R.id.btn_back);
    }

    Button.OnClickListener backDiary = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            WriteActivity.this.finish();
        }
    };

    Button.OnClickListener save = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            input_title = (EditText)findViewById(R.id.input_title);
            input_content = (EditText)findViewById(R.id.input_content);
            //db.insert("20200127", input_title.toString(), input_content.toString());

        }
    };


}