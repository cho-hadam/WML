package com.jmhd.wml;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

public class DateClickListener implements View.OnClickListener {
    private Context context;
    private LinearLayout activeBox;

    public DateClickListener(Context context) {
        this.context = context;
        this.activeBox = (LinearLayout) ((Activity)context).findViewById(R.id.active_box);
    }

    @Override
    public void onClick(View view) {
        if(activeBox.getVisibility() == View.INVISIBLE) {
            activeBox.setVisibility(View.VISIBLE);
        } else {
            activeBox.setVisibility(View.INVISIBLE);
        }
    }
}
