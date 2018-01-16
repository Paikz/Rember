package com.remberapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomList extends ArrayAdapter<String>{

    private final Activity context;
    private final ArrayList<String> titleArr;
    private final ArrayList<String> timeArr;
    private final ArrayList<String> dateArr;
    private final ArrayList<String> fromArr;
    public CustomList(Activity context,
                      ArrayList<String> titleArr, ArrayList<String> timeArr, ArrayList<String> dateArr, ArrayList<String> fromArr) {
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

        title.setText(titleArr.get(position));
        time.setText(timeArr.get(position));
        date.setText(dateArr.get(position));
        from.setText("from " + fromArr.get(position));

        return rowView;
    }
}
