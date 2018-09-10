package com.hap.trip.adapter.pager;

import android.content.res.Resources;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hap.trip.TripApplication;

import javax.inject.Inject;

public abstract class BasePagerAdapter extends FragmentPagerAdapter {
    @Inject
    protected Resources resources;

    BasePagerAdapter(FragmentManager fm) {
        super(fm);

        TripApplication.getInstance().inject(this);
    }
}
