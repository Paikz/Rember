package com.remberapp.NewReminder;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.remberapp.HomeActivity;
import com.remberapp.R;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateRem2Activity extends AppCompatActivity {
    Bundle bundle = new Bundle();
    DateFormat formatDateTime = DateFormat.getDateTimeInstance();
    TextView dateField;
    TextView titleView;
    private String title = "";
    private String description = "";
    private Button btn_date;
    private Button btn_time;
    Calendar dateTime = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_rem2);
        titleView = findViewById(R.id.title);
        btn_date = findViewById(R.id.button2);
        btn_time = findViewById(R.id.button3);
        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDate();
            }
        });

        btn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTime();
            }
        });
        dateField = findViewById(R.id.date);

        String currentDate = formatDateTime.format(dateTime.getTime());
        dateField.setText(currentDate);

        Intent intentExtras = getIntent();
        bundle = intentExtras.getExtras();

        if (bundle != null) {
            title = bundle.getString("Title");
            description = bundle.getString("Description");
        }
        titleView.setText(title);

        dateField.setText(currentDate);
    }

    private void updateDate() {
        new DatePickerDialog(this, d, dateTime.get(Calendar.YEAR), dateTime.get(Calendar.MONTH), dateTime.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateTime() {
        new TimePickerDialog(this, t, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE), true).show();
    }

    private void updateDateLabel() {

        dateField.setText(formatDateTime.format(dateTime.getTime()));
    }

    public void GoBack(View view) {
        Intent intent = new Intent(this, CreateRem1Activity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            dateTime.set(Calendar.YEAR, i);
            dateTime.set(Calendar.MONTH, i1);
            dateTime.set(Calendar.DAY_OF_MONTH, i2);
            btn_date.setBackgroundResource(R.drawable.rounded_shape);
            btn_date.setTextColor(Color.WHITE);

            updateDateLabel();
        }
    };

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            dateTime.set(Calendar.HOUR_OF_DAY, i);
            dateTime.set(Calendar.MINUTE, i1);
            btn_time.setBackgroundResource(R.drawable.rounded_shape);
            btn_time.setTextColor(Color.WHITE);

            updateDateLabel();
        }
    };

    public void ToStage3 (View view) {
        bundle.putString("Date", formatDateTime.format(dateTime.getTime()));
        Intent intent = new Intent(this, CreateRem3Activity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
