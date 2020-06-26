package com.jmhd.wml;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Calendar;

public class CalendarAdapter extends BaseAdapter {
    // activity 참조
    Context context = null;
    TextView date = null;

    public CalendarAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int maxDate = getMaxDate();
        Log.d("MyLog",String.valueOf(convertView));

        if (convertView == null) {
            for (int i = 1; i <= maxDate; i++) {
                date = new TextView(context);
                date.setText(i);
                Log.d("day", String.valueOf(i));
            }
        } else {
            date = (TextView) convertView;
        }

        return date;
    }

    private int getMaxDate() {
        Calendar calendar = Calendar.getInstance();
        int maxDate = calendar.getActualMaximum(Calendar.DATE);
        return maxDate;
    }
}
