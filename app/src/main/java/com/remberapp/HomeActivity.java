package com.remberapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.remberapp.NewReminder.CreateRem3Activity;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(this);

        ListView list;

        String[] titleArr = {
                "Go Shopping!",
                "Clean up room",
                "Cinema tonight"
        };
        String[] timeArr = {
                "20:00",
                "13:30",
                "18:10"
        };
        String[] dateArr = {
                "10-12-2017",
                "11-12-2017",
                "12-12-2017"
        };
        String[] fromArr = {
                "Zacke",
                "+46700321223",
                "0794434587"
        };

        String[] descriptions = {
                "Ye dude, we need food. Go buy something dawg.",
                "Looks like a mess. Clean everything up before I get home!",
                "STAR WARS HYYYYPE!"
        };


        CustomList adapter = new
                CustomList(HomeActivity.this, titleArr, timeArr, dateArr, fromArr);
        list=(ListView)findViewById(R.id.OtherReminders);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                AlertDialog alertDialog = new AlertDialog.Builder(HomeActivity.this).create();
                alertDialog.setCanceledOnTouchOutside(true);
                alertDialog.setTitle(titleArr[position]);
                alertDialog.setMessage(descriptions[position]);
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Okay",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();

            }
        });
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_home;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_home;
    }
}
