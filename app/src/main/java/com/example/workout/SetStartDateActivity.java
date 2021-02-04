package com.example.workout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;

public class SetStartDateActivity extends AppCompatActivity {

    private Date currentStartDate;

    protected Date getCurrentStartDate() {
        return currentStartDate;
    }

    protected void setCurrentStartDate(Date startDate) {
        currentStartDate = startDate;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_start_date);

        final Calendar cal = Calendar.getInstance();

        setCurrentStartDate(AppWideResourceWrapper.retrieveStartDate());
        cal.setTime(getCurrentStartDate());

        final Button saveButton = (Button) findViewById(R.id.saveStartDate);
        final Button revertButton = (Button) findViewById(R.id.resetStartDate);
        final Button returnButton = (Button) findViewById(R.id.returnFromSetStartDate);

        final DatePicker startDatePicker = (DatePicker) findViewById(R.id.startDate);
        startDatePicker.updateDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        startDatePicker.setMaxDate((new Date()).getTime());
        startDatePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                cal.set(year,monthOfYear,dayOfMonth);
                AppWideResourceWrapper.setStartDate(cal.getTime(), false);
                boolean hideButtons = getCurrentStartDate().equals(cal.getTime());
                saveButton.setVisibility(hideButtons?View.GONE:View.VISIBLE);
                revertButton.setVisibility(hideButtons?View.GONE:View.VISIBLE);
            }
        });

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cal.setTime(getCurrentStartDate());
                startDatePicker.updateDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
                finish();
            }
        });

        saveButton.setOnClickListener(new SaveButtonThread(this, cal));

        revertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cal.setTime(getCurrentStartDate());
                startDatePicker.updateDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
            }
        });
    }
}

class SaveButtonThread extends Thread implements View.OnClickListener {

    SetStartDateActivity parent = null;
    Calendar cal = null;

    public SaveButtonThread(SetStartDateActivity parent, Calendar cal) {
        this.parent = parent;
        this.cal = cal;
    }

    @Override
    public void onClick(View v) {
        this.start();
    }

    @Override
    public void run() {
        Intent intent = new Intent(parent, HighLevelView.class);
        parent.setCurrentStartDate(cal.getTime());
        AppWideResourceWrapper.setStartDate(parent.getCurrentStartDate(), true);
        parent.finish();
        parent.startActivity(intent);
    }
}