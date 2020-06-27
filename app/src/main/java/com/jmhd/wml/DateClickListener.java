package com.jmhd.wml;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

public class DateClickListener implements View.OnClickListener {
    private Context context;
    private LinearLayout activeBox;
    private TextView date;
    private LinearLayout round;

    public DateClickListener(Context context, TextView date) {
        this.context = context;
        this.activeBox = (LinearLayout) ((Activity)context).findViewById(R.id.active_box);
        this.date = date;
        this.round = (LinearLayout) date.getParent();
    }

    @Override
    public void onClick(View view) {
        if(activeBox.getVisibility() == View.INVISIBLE) {
            activeBox.setVisibility(View.VISIBLE);
            round.setBackground(ContextCompat.getDrawable(context, R.drawable.round));
        } else {
            activeBox.setVisibility(View.INVISIBLE);
            round.setBackground(null);
        }
    }
}
