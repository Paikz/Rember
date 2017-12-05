package com.remberapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Register2Activity extends AppCompatActivity {
    String number = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        Context context = getApplicationContext();
        TextView InfoText = findViewById(R.id.InfoText);
        Intent intentExtras = getIntent();
        Bundle bundle = intentExtras.getExtras();

        Boolean Cooldown = false;

        //Getting info from bundle
        if (bundle != null) {
            number = bundle.getString("Number");
        }

        Toast.makeText(context, "Code has been sent", Toast.LENGTH_LONG).show();

        SpannableString ss = new SpannableString("Have you not received your verification code? Send Again");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                /**
                 * @Cooldown = Boolean. Checks if code can be sent again.
                 */
                if (!Cooldown) {
                    //TODO: Send code Again.
                    Toast.makeText(context, "Code has been sent", Toast.LENGTH_LONG).show();
                } else {
                    //TODO: Can't send code. Cooldown.
                    Toast.makeText(context, "On Cooldown", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };
        ss.setSpan(clickableSpan, 46, 56, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        InfoText.setText(ss);
        InfoText.setMovementMethod(LinkMovementMethod.getInstance());
        InfoText.setHighlightColor(Color.WHITE);

        //TODO: "Send Code" action can be done here. @number - Contains mobile number.
    }

    public void ConfirmReg(View view) {
        //TODO: "Register" button action. AKA Confirm Registration.

        //Redirect to Home
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
