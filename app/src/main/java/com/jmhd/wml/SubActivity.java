package com.jmhd.wml;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SubActivity extends AppCompatActivity {
    private Button btn_calendar;
    private ListView list;

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
        SetDiary_list();

    }
    public void SetDiary_list(){
        list = (ListView)findViewById(R.id.diary_list);
        List<String> data = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        list.setAdapter(adapter);

        data.add("점심 두 그릇 먹은 날");
        data.add("제목 못 정한 날");
        data.add("저녁은 치킨");
        data.add("다연이랑 데이트");
        data.add("체육 너무 힘든 날");
        data.add("증명사진 찍으러 간날");
        data.add("프로젝트 시작");
        data.add("치킨 먹고 싶다");
        data.add("청년다방 맛있다");
        data.add("힘든 지민이");
        adapter.notifyDataSetChanged();
    }
}