package com.remberapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomList extends ArrayAdapter<String>{

    private final Activity context;
    private final String[] titleArr;
    private final String[] timeArr;
    private final String[] dateArr;
    private final String[] fromArr;
    public CustomList(Activity context,
                      String[] titleArr, String[] timeArr, String[] dateArr, String[] fromArr) {
        super(context, R.layout.list_item, titleArr);
        this.context = context;
        this.titleArr = titleArr;
        this.timeArr = timeArr;
        this.dateArr = dateArr;
        this.fromArr = fromArr;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_item, null, true);
        TextView title = (TextView) rowView.findViewById(R.id.title);
        TextView time = (TextView) rowView.findViewById(R.id.time);
        TextView date = (TextView) rowView.findViewById(R.id.date);
        TextView from = (TextView) rowView.findViewById(R.id.from);

        title.setText(titleArr[position]);
        time.setText(timeArr[position]);
        date.setText(dateArr[position]);
        from.setText("from " + fromArr[position]);

        return rowView;
    }
}
