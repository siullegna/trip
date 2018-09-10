package com.hap.trip.ui.airport;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;

import com.hap.trip.R;
import com.hap.trip.adapter.pager.AirportPagerAdapter;
import com.hap.trip.model.location.LocationAirportItem;
import com.hap.trip.widget.CustomViewPager;

import butterknife.BindView;

public class OriginAirportActivity extends BaseAirportActivity {
    @BindView(R.id.tl_flight)
    TabLayout tlFlight;
    @BindView(R.id.vp_flight)
    CustomViewPager vpFlight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_origin_airport_search);
        super.onCreate(savedInstanceState);

        final AirportPagerAdapter airportPagerAdapter = new AirportPagerAdapter(getSupportFragmentManager());

        vpFlight.setAdapter(airportPagerAdapter);

        tlFlight.setupWithViewPager(vpFlight);

        showHomeButton();
    }

    @Override
    public void onOriginSelected(LocationAirportItem origin) {
        final Intent result = new Intent();
        result.putExtra(ARG_SELECTED_FLIGHT_KEY, origin);
        setResult(result);
    }
}