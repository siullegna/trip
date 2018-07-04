package com.hap.trip.ui.passenger;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hap.trip.R;
import com.hap.trip.ui.base.BaseAppActivity;

/**
 * Created by luis on 6/26/18.
 */

public class SelectPassengerActivity extends BaseAppActivity {
    public static final String ARG_ADULT_KEY = "com.hap.trip.ui.passenger.ARG_ADULT_KEY";
    public static final String ARG_CHILDREN_KEY = "com.hap.trip.ui.passenger.ARG_CHILDREN_KEY";
    public static final String ARG_INFANT_KEY = "com.hap.trip.ui.passenger.ARG_INFANT_KEY";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_select_passenger);
        super.onCreate(savedInstanceState);
    }
}