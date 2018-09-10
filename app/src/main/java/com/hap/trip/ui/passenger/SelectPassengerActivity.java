package com.hap.trip.ui.passenger;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.AppCompatButton;
import android.view.MenuItem;
import android.view.View;

import com.hap.trip.R;
import com.hap.trip.ui.base.BaseAppActivity;
import com.hap.trip.widget.PassengerPickerView;
import com.hap.trip.widget.PassengerPickerViewGroup;

import butterknife.BindView;

/**
 * Created by luis on 6/26/18.
 */

public class SelectPassengerActivity extends BaseAppActivity {
    public static final String ARG_ADULT_KEY = "com.hap.trip.ui.passenger.ARG_ADULT_KEY";
    public static final String ARG_CHILDREN_KEY = "com.hap.trip.ui.passenger.ARG_CHILDREN_KEY";
    public static final String ARG_INFANT_KEY = "com.hap.trip.ui.passenger.ARG_INFANT_KEY";

    @BindView(R.id.adult_picker)
    PassengerPickerViewGroup adultPicker;
    @BindView(R.id.children_picker)
    PassengerPickerViewGroup childrenPicker;
    @BindView(R.id.infant_picker)
    PassengerPickerViewGroup infantPicker;
    @BindView(R.id.btn_continue)
    AppCompatButton btnContinue;

    private int adultCount = 1; // default is 1
    private int childrenCount = 0;
    private int infantCount = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_select_passenger);
        super.onCreate(savedInstanceState);

        showHomeButton();
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.flight_passenger);
        }

        if (savedInstanceState != null) {
            adultCount = savedInstanceState.getInt(ARG_ADULT_KEY, 1);
            childrenCount = savedInstanceState.getInt(ARG_CHILDREN_KEY, 0);
            infantCount = savedInstanceState.getInt(ARG_INFANT_KEY, 0);
        } else if (getIntent().getExtras() != null) {
            final Bundle args = getIntent().getExtras();
            adultCount = args.getInt(ARG_ADULT_KEY, 1);
            childrenCount = args.getInt(ARG_CHILDREN_KEY, 0);
            infantCount = args.getInt(ARG_INFANT_KEY, 0);
        }

        adultPicker.setCount(adultCount, new PassengerPickerView.PickerCountListener() {
            @Override
            public void onCountChange(int countValue) {
                adultCount = countValue;
                checkPassengerValidation();
            }
        });
        childrenPicker.setCount(childrenCount, new PassengerPickerView.PickerCountListener() {
            @Override
            public void onCountChange(int countValue) {
                childrenCount = countValue;
                checkPassengerValidation();
            }
        });
        infantPicker.setCount(infantCount, new PassengerPickerView.PickerCountListener() {
            @Override
            public void onCountChange(int countValue) {
                infantCount = countValue;
                checkPassengerValidation();
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult();
            }
        });

        checkPassengerValidation();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(ARG_ADULT_KEY, adultCount);
        outState.putInt(ARG_CHILDREN_KEY, childrenCount);
        outState.putInt(ARG_INFANT_KEY, infantCount);
        super.onSaveInstanceState(outState);
    }

    private void setResult() {
        final Intent result = new Intent();
        result.putExtra(ARG_ADULT_KEY, adultCount);
        result.putExtra(ARG_CHILDREN_KEY, childrenCount);
        result.putExtra(ARG_INFANT_KEY, infantCount);

        setResult(result);
    }

    private void checkPassengerValidation() {
        btnContinue.setEnabled(adultCount + childrenCount + infantCount > 0);
    }
}