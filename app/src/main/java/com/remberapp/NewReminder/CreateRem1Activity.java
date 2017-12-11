package com.remberapp.NewReminder;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.remberapp.HomeActivity;
import com.remberapp.R;
import com.remberapp.Register2Activity;

public class CreateRem1Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_rem1);

        EditText titleField = findViewById(R.id.title);
        Button button = findViewById(R.id.button);

        titleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence str, int start, int count, int after)    {

            }

            @Override
            public void onTextChanged(CharSequence str, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable str) {
                if(str.toString().trim().length()<1) {
                    button.setBackgroundResource(R.drawable.rounded_shape_gray);
                    button.setTextColor(Color.GRAY);
                }else{
                    button.setBackgroundResource(R.drawable.rounded_shape);
                    button.setTextColor(Color.WHITE);
                }
            }
        });
    }

    public void GoBack(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void ToStage2(View view) {
        EditText titleView = findViewById(R.id.title);
        String title = titleView.getText().toString();

        EditText descriptionView = findViewById(R.id.description);
        String description = descriptionView.getText().toString();

        if (!title.isEmpty()) {
            Intent intentBundle = new Intent(this, CreateRem2Activity.class);
            Bundle args = new Bundle();
            args.putString("Title", title);
            args.putString("Description", description);
            intentBundle.putExtras(args);
            startActivity(intentBundle);
        } else {
            titleView.setError("This field can not be blank");
        }
    }

}
