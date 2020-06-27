package com.jmhd.wml;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class WriteActivity extends AppCompatActivity {
    private ImageButton btn_save;
    private EditText input_title;
    private EditText input_content;


    DBHelper db = new DBHelper(getApplicationContext(), "DIARY.db", null, 1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        btn_save.setOnClickListener(save);
    }

    Button.OnClickListener save = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            input_title = (EditText)findViewById(R.id.input_title);
            input_content = (EditText)findViewById(R.id.input_content);
            //db.insert(date, input_title, input_content);
        }
    };


}