package com.hap.trip.ui.flight;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.hap.trip.R;
import com.hap.trip.adapter.FlightPagerAdapter;
import com.hap.trip.ui.base.BaseAppActivity;
import com.hap.trip.util.DeviceUtil;
import com.hap.trip.widget.CustomViewPager;

/**
 * Created by luis on 6/23/18.
 */

public class FlightActivity extends BaseAppActivity {
    private CustomViewPager vpFlight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_fligth);
        super.onCreate(savedInstanceState);

        final TabLayout tlFlight = findViewById(R.id.tl_flight);
        vpFlight = findViewById(R.id.vp_flight);

        final FlightPagerAdapter flightPagerAdapter = new FlightPagerAdapter(getSupportFragmentManager());

        vpFlight.setAdapter(flightPagerAdapter);

        tlFlight.setupWithViewPager(vpFlight);

        vpFlight.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                DeviceUtil.hideKeyboard(vpFlight, FlightActivity.this);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
