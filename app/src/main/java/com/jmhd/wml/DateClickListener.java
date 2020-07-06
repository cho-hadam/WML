package com.jmhd.wml;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

public class DateClickListener implements View.OnClickListener {
    private Context context;
    private LinearLayout activeBox;
    private LinearLayout round;
    private TextView date; // 날짜 TextView
    private DateInfo dateInfo; // 날짜 정보 객체
    private String now;
    private AlertDialog.Builder builder;

    public DateClickListener(Context context, TextView date, DateInfo dateInfo) {
        this.context = context;
        this.activeBox = (LinearLayout) ((Activity)context).findViewById(R.id.active_box);
        this.round = (LinearLayout) date.getParent();
        this.date = date;
        this.dateInfo = dateInfo;
        this.now = dateInfo.getNow();
    }

    private void showDialog() {
        builder = new AlertDialog.Builder(context);
        builder.setTitle("미래를 선택하셨습니다");
        builder.setMessage("당일 혹은 이전 날짜를 선택해주세요.");
        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // 예 클릭시 event 처리
                    }
                });
        builder.show();
    }

    @Override
    public void onClick(View view) {
        String clickDate = (String) date.getText();
        if (date.getText().length() == 1){ // 월이 한 자릿수일 때
            // 앞에 0 추가
            clickDate = "0" + date.getText();
        }
        if(activeBox.getVisibility() == View.INVISIBLE) { // 메뉴가 보이지 않으면
            if (!clickDate.equals("00")) { // 누른 날짜가 0이 아니면
                if (Integer.parseInt(dateInfo.getYear()+ dateInfo.getMonth()+clickDate) <= Integer.parseInt(now)) {
                    // 누른 날짜가 현재 날짜보다 이전이면 실행
                    dateInfo.setDate(clickDate); // 날짜 정보에 누른 날짜 저장
                    activeBox.setVisibility(View.VISIBLE); // 메뉴 보이게
                    round.setBackground(ContextCompat.getDrawable(context, R.drawable.round)); // 동그라미 보이게
                } else {
                    showDialog();
                }
            }
        } else {
            if (dateInfo.getDate().equals(clickDate)){
                activeBox.setVisibility(View.INVISIBLE);
                round.setBackground(null);
            }
        }
    }
}
