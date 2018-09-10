package com.hap.trip.ui.airport;

import android.support.design.widget.Snackbar;

import com.hap.trip.R;
import com.hap.trip.model.location.LocationAirportItem;
import com.hap.trip.ui.base.BaseAppActivity;

public abstract class BaseAirportActivity extends BaseAppActivity implements BaseAirportFragment.OnAirportListener {
    public static final String ARG_SELECTED_FLIGHT_KEY = "com.hap.trip.ui.airport.ARG_SELECTED_FLIGHT_KEY";

    @Override
    public void onOriginSelected(LocationAirportItem origin) {

    }

    @Override
    public void onDestinationSelected(LocationAirportItem destination) {

    }

    @Override
    public void onAirportLoadeError() {
        showSnackBar(getString(R.string.error_airport_list), Snackbar.LENGTH_LONG);
    }
}
