package com.remberapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OptionGeneralActivity extends AppCompatActivity {
    String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_general);

        TextView nameView = findViewById(R.id.name);
        Intent intentExtras = getIntent();
        Bundle bundle = intentExtras.getExtras();

        //Getting info from bundle
        if (bundle != null) {
            name = bundle.getString("Name");
            nameView.setText(name);
        }
    }
}
