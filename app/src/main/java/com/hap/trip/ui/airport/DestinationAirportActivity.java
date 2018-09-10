package com.hap.trip.ui.airport;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.hap.trip.R;
import com.hap.trip.model.location.LocationAirportItem;

public class DestinationAirportActivity extends BaseAirportActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_destination_airport_search);
        super.onCreate(savedInstanceState);

        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        final Fragment previous = getSupportFragmentManager().findFragmentByTag("fragment");
        if (previous != null) {
            ft.remove(previous);
        }

        final Fragment current = AirportListSearchFragment.getInstance(false, getString(R.string.flight_destination_hint), R.drawable.ic_plane_arrive);
        ft.add(R.id.fragment_container, current, "fragment");
        ft.commit();

        showHomeButton();
    }

    @Override
    public void onDestinationSelected(LocationAirportItem destination) {
        final Intent result = new Intent();
        result.putExtra(ARG_SELECTED_FLIGHT_KEY, destination);
        setResult(result);
    }
}