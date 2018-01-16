package com.remberapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.remberapp.NewReminder.CreateRem3Activity;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HomeActivity extends BaseActivity {
    LinearLayout list;
    LinearLayout list2;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private Handler mHandler = new Handler();


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("reminders");

        navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(this);

        Map<String, String> listProp = new HashMap<String, String>();

        list=(LinearLayout) findViewById(R.id.OtherReminders);
        list2=(LinearLayout) findViewById(R.id.YourReminders);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        String userNr = "0" + currentUser.getPhoneNumber().substring(3, currentUser.getPhoneNumber().length());

        ref.child(userNr).addValueEventListener(new ValueEventListener() {
            String sender;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sender = "";
                list.removeAllViews();
                list2.removeAllViews();

                // Get Post object and use the values to update the UI
                ArrayList<String> titleArr1 = new ArrayList<String>();
                ArrayList<String> timeArr1 = new ArrayList<String>();
                ArrayList<String> dateArr1 = new ArrayList<String>();
                ArrayList<String> fromArr1 = new ArrayList<String>();
                ArrayList<String> descArr1 = new ArrayList<String>();

                ArrayList<String> titleArr2 = new ArrayList<String>();
                ArrayList<String> timeArr2 = new ArrayList<String>();
                ArrayList<String> dateArr2 = new ArrayList<String>();
                ArrayList<String> fromArr2 = new ArrayList<String>();
                ArrayList<String> descArr2 = new ArrayList<String>();


                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String title = ds.child("title").getValue(String.class);
                    String time = ds.child("date").getValue(String.class).substring(13, 18);
                    String date = ds.child("date").getValue(String.class).substring(0, 12);
                    String disc = ds.child("description").getValue(String.class);
                    String from = ds.child("from").getValue(String.class);
                    String newRem = ds.child("new").getValue(String.class);
                    sender = from;

                    if (sender == currentUser.getPhoneNumber()  ||
                            sender == userNr) {
                        sender = "yourself";
                    }

                    long realDate = System.currentTimeMillis();

                    if (newRem.equals("new")) {

                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy HH:mm:ss");
                            Date formDate = sdf.parse(ds.child("date").getValue(String.class));

                            realDate = formDate.getTime();


                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        addNotification(sender, "", title, disc);
                        mHandler.postDelayed(new Runnable() {
                            public void run() {
                                addNotification(sender, "NOW", title, disc);
                            }
                        }, realDate - System.currentTimeMillis());

                        ref.child(userNr + "/" + ds.getKey() + "/new").setValue("old");
                    }

                    if (from.equals(currentUser.getPhoneNumber()) || from.equals(userNr)) {
                        titleArr1.add(title);
                        timeArr1.add(time);
                        dateArr1.add(date);
                        fromArr1.add(sender);
                        descArr1.add(disc);
                    } else {
                        titleArr2.add(title);
                        timeArr2.add(time);
                        dateArr2.add(date);
                        fromArr2.add(from);
                        descArr2.add(disc);
                    }
                }


                CustomList newAdapter = new CustomList(HomeActivity.this, titleArr1, timeArr1, dateArr1, fromArr1);
                CustomList newAdapter2 = new CustomList(HomeActivity.this, titleArr2, timeArr2, dateArr2, fromArr2);

                for (int i = 0; i < titleArr1.size(); i++) {
                    View item = newAdapter.getView(i, null, null);
                    list2.addView(item);
                }
                for (int i = 0; i < titleArr2.size(); i++) {
                    View item = newAdapter2.getView(i, null, null);
                    list.addView(item);
                }

                for(int i= 0 ; i < list2.getChildCount() ; i++){
                    View view  = list2.getChildAt(i);
                    view.setTag(i);
                    view.setOnClickListener(new View.OnClickListener(){

                        @Override
                        public void onClick(View v){
                            int position = (int) v.getTag();
                            AlertDialog alertDialog = new AlertDialog.Builder(HomeActivity.this).create();
                            alertDialog.setCanceledOnTouchOutside(true);
                            alertDialog.setTitle(titleArr1.get(position));
                            alertDialog.setMessage(descArr1.get(position));
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

                for(int i= 0 ; i < list.getChildCount() ; i++){
                    View view  = list.getChildAt(i);
                    view.setTag(i);
                    view.setOnClickListener(new View.OnClickListener(){

                        @Override
                        public void onClick(View v){
                            int position = (int) v.getTag();
                            AlertDialog alertDialog = new AlertDialog.Builder(HomeActivity.this).create();
                            alertDialog.setCanceledOnTouchOutside(true);
                            alertDialog.setTitle(titleArr2.get(position));
                            alertDialog.setMessage(descArr2.get(position));
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


                newAdapter.notifyDataSetChanged();
                newAdapter2.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(HomeActivity.this,"Could not connect to DB!", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void addNotification(String sender, String when, String title, String disc) {
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        long startDate = System.currentTimeMillis();

        if (when.equals("")) {
            builder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(R.drawable.ic_remberwhite)
                            .setContentTitle("You got a reminder!")
                            .setContentText(title + " /" + sender)
                            .setSound(sound);
        } else {

            builder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(R.drawable.ic_remberwhite)
                            .setContentTitle(title)
                            .setContentText(disc)
                            .setSound(sound)
                            .setWhen(System.currentTimeMillis());
        }

        Intent notificationIntent = new Intent(this, HomeActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
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
