package com.hap.trip.ui.result;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.hap.trip.R;
import com.hap.trip.ui.base.BaseAppActivity;

public class FlightFilterActivity extends BaseAppActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_flight_filter);
        super.onCreate(savedInstanceState);

        showHomeButton();
    }
}
