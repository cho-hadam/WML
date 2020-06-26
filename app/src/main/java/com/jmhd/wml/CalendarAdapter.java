package com.jmhd.wml;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

class Date {
    private int date;

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
}

public class CalendarAdapter extends BaseAdapter {
    private ArrayList<Date> dateArrayList = new ArrayList<Date>() ;
    // activity 참조
    Context context = null;
    TextView date = null;

    public CalendarAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return dateArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return dateArrayList.get(position);
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
        }

        date = (TextView) convertView.findViewById(R.id.text_date);

        Date dateViewItem = dateArrayList.get(position);
        date.setText(String.valueOf(dateViewItem.getDate()));

        return convertView;
    }

    protected int getMaxDate() {
        Calendar calendar = Calendar.getInstance();
        int maxDate = calendar.getActualMaximum(Calendar.DATE);
        return maxDate;
    }

    public void setDate() {
        for (int i = 1; i <= getMaxDate(); i++) {
            Date d = new Date();
            d.setDate(i);
            dateArrayList.add(d);
        }
    }
}
