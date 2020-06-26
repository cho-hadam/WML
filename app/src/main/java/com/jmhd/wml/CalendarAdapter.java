package com.jmhd.wml;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class CalendarAdapter extends BaseAdapter {
    private ArrayList<Date> dateArrayList = new ArrayList<Date>() ;
    // activity 참조
    Context context = null;
    TextView date = null;
    private int year;
    private int get_month;

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
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.calendar_date, parent, false);
        }

        date = (TextView) convertView.findViewById(R.id.text_date);

        Date dateViewItem = dateArrayList.get(position);

        date.setText(String.valueOf(dateViewItem.getDate()));
        date.setTextColor(Color.parseColor(dateViewItem.getTextColor()));

        return convertView;
    }

    protected int getMaxDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, get_month, 1);
        int maxDate = calendar.getActualMaximum(Calendar.DATE);
        return maxDate;
    }

    protected String getTextColor(int date_count) {
        String[] textColor = {
                "#FF0000", "#000000", "#000000", "#000000", "#000000", "#000000", "#2400FF"
        };

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, get_month, date_count);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        return textColor[dayOfWeek-1];
    }

    public void emptyDate() {
        for (int i = 1; i < getStartDayOfWeek(); i++) {
            Date empty = new Date();
            empty.setDate(0);
            empty.setTextColor("#ffffff");
            dateArrayList.add(empty);
        }
    }

    public void setDate(int year, int get_month) {
        this.year = year;
        this.get_month = get_month;

        emptyDate();

        for (int i = 1; i <= getMaxDate(); i++) {
            Date d = new Date();
            d.setDate(i);
            d.setTextColor(getTextColor(i));
            dateArrayList.add(d);
        }
    }

    public int getStartDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, get_month, 1);
        int start_dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        return start_dayOfWeek;
    }
}
