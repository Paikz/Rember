package com.remberapp;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class RegisterActivity extends AppCompatActivity {
    Boolean Registered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //TODO: Check if user is registered. @Registered = true if user is. DO NOTHING if not.

        if (Registered) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
    }

    public void ToRegister2(View view)
    {
        EditText note_nr = findViewById(R.id.Number);
        String numberStr = note_nr.getText().toString();

        if (!numberStr.isEmpty()) {
            //Creating bundle & Redirecting data to Register2Activity
            Intent intentBundle = new Intent(this, Register2Activity.class);
            Bundle args = new Bundle();
            args.putString("Number", numberStr);
            intentBundle.putExtras(args);
            startActivity(intentBundle);
        } else {
            note_nr.setError("This field can not be blank");
        }
    }
}
