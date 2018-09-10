package com.hap.trip.ui.date;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by luis on 6/26/18.
 */

public class DatePickerActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, DatePickerDialog.OnCancelListener {
    public static final String ARG_MIN_DATE_KEY = "com.hap.trip.ui.date.ARG_MIN_DATE_KEY";
    public static final String RESULT_SELECTED_DATE_KEY = "com.hap.trip.ui.date.RESULT_SELECTED_DATE_KEY";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final long minDate = getIntent().getLongExtra(ARG_MIN_DATE_KEY, System.currentTimeMillis());

        final Date date = new Date(minDate);

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        final DatePickerDialog datePickerDialog =
                new DatePickerDialog(this, this,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.getDatePicker().setMinDate(minDate);
        datePickerDialog.setOnCancelListener(this);

        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        final Date date = calendar.getTime();

        final Intent result = new Intent();
        result.putExtra(RESULT_SELECTED_DATE_KEY, date.getTime());
        setResult(RESULT_OK, result);
        finish();
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        setResult(RESULT_CANCELED);
        finish();
    }
}
