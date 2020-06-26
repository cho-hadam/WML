package com.jmhd.wml;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
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
        Log.d("MyLog","확인");
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.calendar_date, parent, false);
            date = (TextView) convertView.findViewById(R.id.text_date);

            for (int i = 1; i <= getMaxDate(); i++) {
                date.setText(i);
            }
        } else {
            date = (TextView) convertView;
        }

        return convertView;
    }

    private int getMaxDate() {
        Calendar calendar = Calendar.getInstance();
        int maxDate = calendar.getActualMaximum(Calendar.DATE);
        return maxDate;
    }
}
