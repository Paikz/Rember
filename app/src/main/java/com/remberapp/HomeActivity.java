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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.remberapp.NewReminder.CreateRem3Activity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class HomeActivity extends BaseActivity {
    ListView list;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("reminders/0700412743");

        navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(this);

        ArrayList<String> titleArr = new ArrayList<String>();
        ArrayList<String> timeArr = new ArrayList<String>();
        ArrayList<String> dateArr = new ArrayList<String>();
        ArrayList<String> fromArr = new ArrayList<String>();
        ArrayList<String> descArr = new ArrayList<String>();


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
                alertDialog.setTitle(titleArr.get(position));
                alertDialog.setMessage(descArr.get(position));
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Okay",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                ArrayList<String> titleArr2 = new ArrayList<String>();
                ArrayList<String> timeArr2 = new ArrayList<String>();
                ArrayList<String> dateArr2 = new ArrayList<String>();
                ArrayList<String> fromArr2 = new ArrayList<String>();
                ArrayList<String> descArr2 = new ArrayList<String>();

                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    String title = ds.child("title").getValue(String.class);
                    String time = ds.child("date").getValue(String.class).substring(13, 18);
                    String date = ds.child("date").getValue(String.class).substring(0, 12);
                    String disc = ds.child("discription").getValue(String.class);
                    String from = ds.child("from").getValue(String.class);
                    titleArr2.add(title);
                    timeArr2.add(time);
                    dateArr2.add(date);
                    fromArr2.add(from);
                    descArr2.add(disc);
                }
                //TODO:notis trigger


                CustomList newAdapter = new CustomList(HomeActivity.this, titleArr2, timeArr2, dateArr2, fromArr2);
                list.setAdapter(newAdapter);
                newAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(HomeActivity.this,"Could not connect to DB!", Toast.LENGTH_LONG).show();
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
