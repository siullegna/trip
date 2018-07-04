package com.hap.trip.adapter;

import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hap.trip.R;
import com.hap.trip.TripApplication;
import com.hap.trip.ui.flight.AirportFragment;
import com.hap.trip.ui.flight.OneWayFragment;
import com.hap.trip.ui.flight.RoundTripFragment;

import javax.inject.Inject;

/**
 * Created by luis on 6/23/18.
 */

public class FlightPagerAdapter extends FragmentPagerAdapter {
    private static final int PAGE_NUMBER = 3;

    @Inject
    protected Resources resources;

    public FlightPagerAdapter(FragmentManager fm) {
        super(fm);
        TripApplication.getInstance().inject(this);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return RoundTripFragment.getInstance();
            case 1:
                return OneWayFragment.getInstance();
            case 2:
                return AirportFragment.getInstance();
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return resources.getString(R.string.flight_tab_title_round_trip);
            case 1:
                return resources.getString(R.string.flight_tab_title_one_way_trip);
            case 2:
                return resources.getString(R.string.flight_tab_title_airports);
            default:
                return super.getPageTitle(position);
        }
    }

    @Override
    public int getCount() {
        return PAGE_NUMBER;
    }
}
