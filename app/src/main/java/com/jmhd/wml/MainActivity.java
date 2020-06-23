package com.jmhd.wml;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void goDiary(View view) {
        Intent intent = new Intent(this, SubActivity.class);
        startActivity(intent);
    }
}