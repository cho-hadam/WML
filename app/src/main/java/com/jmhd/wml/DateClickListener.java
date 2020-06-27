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
    private String dateinfo;

    public DateClickListener(Context context, TextView date, String dateinfo) {
        this.context = context;
        this.activeBox = (LinearLayout) ((Activity)context).findViewById(R.id.active_box);
        this.round = (LinearLayout) date.getParent();
        this.date = date;
        this.dateinfo = dateinfo+date.getText();
        Log.d("MyLog",this.dateinfo);
    }

    @Override
    public void onClick(View view) {
        if(activeBox.getVisibility() == View.INVISIBLE) {
            if (!date.getText().equals("0")) {
                activeBox.setVisibility(View.VISIBLE);
                round.setBackground(ContextCompat.getDrawable(context, R.drawable.round));
            }
        } else {
            activeBox.setVisibility(View.INVISIBLE);
            round.setBackground(null);
        }
    }
}
