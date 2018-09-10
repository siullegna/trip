package com.hap.trip.adapter.pager;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.hap.trip.R;
import com.hap.trip.ui.airport.AirportListSearchFragment;
import com.hap.trip.ui.airport.AirportMapSearchFragment;

public class AirportPagerAdapter extends BasePagerAdapter {
    private static final int PAGE_NUMBER = 2;

    public AirportPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return AirportListSearchFragment.getInstance(true, resources.getString(R.string.flight_origin_hint), R.drawable.ic_plane_depart);
            case 1:
                return AirportMapSearchFragment.getInstance();
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return resources.getString(R.string.search_airport_list);
            case 1:
                return resources.getString(R.string.search_airport_map);
            default:
                return super.getPageTitle(position);
        }
    }

    @Override
    public int getCount() {
        return PAGE_NUMBER;
    }
}
