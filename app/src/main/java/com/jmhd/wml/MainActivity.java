package com.jmhd.wml;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_diary = (Button) findViewById(R.id.btn_diary);
        btn_diary.setOnClickListener(new View.onClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(this, SubActivity.class);
                startActivity(intent);
            }
        });

    }

    public void goDiary(View view) {

    }
}