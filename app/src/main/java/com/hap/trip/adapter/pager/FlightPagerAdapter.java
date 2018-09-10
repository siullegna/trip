package com.hap.trip.adapter.pager;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.hap.trip.R;
import com.hap.trip.ui.flight.OneWayFragment;
import com.hap.trip.ui.flight.RoundTripFragment;

/**
 * Created by luis on 6/23/18.
 */

public class FlightPagerAdapter extends BasePagerAdapter {
    private static final int PAGE_NUMBER = 2;

    public FlightPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return RoundTripFragment.getInstance();
            case 1:
                return OneWayFragment.getInstance();
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
            default:
                return super.getPageTitle(position);
        }
    }

    @Override
    public int getCount() {
        return PAGE_NUMBER;
    }
}
