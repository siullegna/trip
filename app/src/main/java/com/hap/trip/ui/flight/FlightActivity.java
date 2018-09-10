package com.hap.trip.ui.flight;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.hap.trip.Config;
import com.hap.trip.R;
import com.hap.trip.adapter.pager.FlightPagerAdapter;
import com.hap.trip.ui.base.BaseAppActivity;
import com.hap.trip.util.DeviceUtil;
import com.hap.trip.util.IntentFactory;
import com.hap.trip.widget.CustomViewPager;

import butterknife.BindView;

/**
 * Created by luis on 6/23/18.
 */

public class FlightActivity extends BaseAppActivity implements BaseTripFragment.OnSearchFlightEvent {
    @BindView(R.id.tl_flight)
    TabLayout tlFlight;
    @BindView(R.id.vp_flight)
    CustomViewPager vpFlight;
    @BindView(R.id.loading_overlay)
    View loadingOverlay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_fligth);
        super.onCreate(savedInstanceState);

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

        Config.setupAd(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_flight, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_my_flights:
                final Intent intent = IntentFactory.getMyFlightsIntent();
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showLoader() {
        if (loadingOverlay != null) {
            loadingOverlay.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideLoader() {
        if (loadingOverlay != null) {
            loadingOverlay.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError() {
        showSnackBar(getString(R.string.flight_result_error), Snackbar.LENGTH_LONG);
    }
}
