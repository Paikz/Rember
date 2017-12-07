package com.remberapp;


import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EditText numberField = findViewById(R.id.Number);
        Button button = findViewById(R.id.button);

        mAuth = FirebaseAuth.getInstance();
        //Uncomment to get to register page again
        //FirebaseAuth.getInstance().signOut();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }

        numberField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence str, int start, int count, int after)    {

            }

            @Override
            public void onTextChanged(CharSequence str, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable str) {
                if(str.toString().trim().length()<10){
                    button.setBackgroundResource(R.drawable.rounded_shape_gray);
                    button.setTextColor(Color.GRAY);
                }else{
                    button.setBackgroundResource(R.drawable.rounded_shape);
                    button.setTextColor(Color.WHITE);
                }
            }
        });
    }

    public void ToRegister2(View view)
    {
        EditText note_nr = findViewById(R.id.Number);
        String numberStr = note_nr.getText().toString();

        if (!numberStr.isEmpty()) {
            if (note_nr.getText().length()<10) {
                note_nr.setError("Number should be 10 characters");
            } else {
                //Creating bundle & Redirecting data to Register2Activity
                Intent intentBundle = new Intent(this, Register2Activity.class);
                Bundle args = new Bundle();
                args.putString("Number", numberStr);
                intentBundle.putExtras(args);
                startActivity(intentBundle);
            }
        } else {
            note_nr.setError("This field can not be blank");
        }
    }
}
