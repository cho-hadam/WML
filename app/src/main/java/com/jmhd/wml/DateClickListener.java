package com.jmhd.wml;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

public class DateClickListener implements View.OnClickListener {
    private Context context;
    private LinearLayout activeBox;
    private LinearLayout round;
    private TextView date;
    private DateInfo dateinfo;

    public DateClickListener(Context context, TextView date, DateInfo dateinfo) {
        this.context = context;
        this.activeBox = (LinearLayout) ((Activity)context).findViewById(R.id.active_box);
        this.round = (LinearLayout) date.getParent();
        this.date = date;
        this.dateinfo = dateinfo;
    }

    @Override
    public void onClick(View view) {

        if(activeBox.getVisibility() == View.INVISIBLE) {
            if (!date.getText().equals("0")) {
                dateinfo.setDate(date.getText().toString());
                activeBox.setVisibility(View.VISIBLE);
                round.setBackground(ContextCompat.getDrawable(context, R.drawable.round));
            }
        } else {
            String clickDate = (String) date.getText();
            if (date.getText().length() == 1){ // 월이 한 자릿수일 때
                // 앞에 0 추가
                 clickDate = "0" + date.getText();
            }
            if (dateinfo.getDate().equals(clickDate)){
                activeBox.setVisibility(View.INVISIBLE);
                round.setBackground(null);
            }

        }
    }
}
