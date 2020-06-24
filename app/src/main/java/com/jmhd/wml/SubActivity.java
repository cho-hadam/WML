package com.jmhd.wml;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SubActivity extends AppCompatActivity {
    private Button btn_calendar;
    private ListView diary_list;
    diary_list_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        btn_calendar = (Button) findViewById(R.id.btn_calendar);
        btn_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubActivity.this, MainActivity.class);
                intent.addFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                overridePendingTransition(0,0);
                SubActivity.this.finish();
            }
        });
        diary_list_view();


    }
    public void diary_list_view(){
        adapter = new diary_list_adapter();
        diary_list = (ListView) findViewById(R.id.diary_list);
        diary_list.setAdapter(adapter);
        diary_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SubActivity.this, DiaryActivity.class);intent.addFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                overridePendingTransition(0,0);
                SubActivity.this.finish();
            }
        });

        adapter.addItem("점심 두 그릇 먹은 날", "20200624");
        adapter.addItem("제목 못 정한 날", "20200625");
    }

}