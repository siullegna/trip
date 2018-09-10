package com.hap.trip.ui.result;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hap.trip.R;
import com.hap.trip.adapter.FlightAdapter;
import com.hap.trip.model.flight.FlightData;
import com.hap.trip.model.flight.FlightItem;
import com.hap.trip.model.search.SearchFlightItem;
import com.hap.trip.ui.base.BaseAppActivity;
import com.hap.trip.util.DateUtil;
import com.hap.trip.util.FlightUtil;
import com.hap.trip.util.IntentFactory;
import com.hap.trip.viewholder.FlightHolder;

import java.util.ArrayList;

import butterknife.BindView;

public class FlightResultActivity extends BaseAppActivity implements FlightHolder.OnFlightClickListener {
    public static final String ARG_FLIGHTS_FOUND_KEY = "com.hap.trip.ui.result.ARG_FLIGHTS_FOUND_KEY";
    public static final String ARG_SEARCH_PARAMS_KEY = "com.hap.trip.ui.result.ARG_SEARCH_PARAMS_KEY";

    private static final int REQUEST_FILTER_CODE = 1001;
    private static final int REQUEST_SAVE_FLIGHT_CODE = 1002;

    @BindView(R.id.filter)
    AppCompatImageView filter;
    @BindView(R.id.rv_results)
    RecyclerView rvResults;

    private SearchFlightItem searchFlightItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_result_flight);
        super.onCreate(savedInstanceState);

        rvResults.setHasFixedSize(false);
        rvResults.setLayoutManager(new LinearLayoutManager(this));
        final FlightAdapter flightAdapter = new FlightAdapter(this);
        rvResults.setAdapter(flightAdapter);

        final Bundle args = getIntent().getExtras();

        if (args != null) {
            final FlightItem flightItem = args.getParcelable(ARG_FLIGHTS_FOUND_KEY);
            searchFlightItem = args.getParcelable(ARG_SEARCH_PARAMS_KEY);
            if (flightItem != null) {
                final ArrayList<FlightData> flightDataList = FlightUtil.getFlightData(flightItem.getResults());
                flightAdapter.addAll(flightDataList);
            }
        }

        showHomeButton();
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.flight_result, searchFlightItem.getDestination()));
            // first we get the value from travelers
            final String travelers = getResources().getQuantityString(R.plurals.flight_result_traveler, FlightUtil.getTotalOfTravelers(searchFlightItem), FlightUtil.getTotalOfTravelers(searchFlightItem));
            actionBar.setSubtitle(getString(R.string.flight_result_date_format, DateUtil.getSelectedDateFormatted(searchFlightItem.getDepartureDate()), travelers));
        }

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = IntentFactory.getFlightFilterIntent(searchFlightItem);
                startActivityForResult(intent, REQUEST_FILTER_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_FILTER_CODE:
                // this will trigger the api call again with the new params
                break;
            case REQUEST_SAVE_FLIGHT_CODE:
                if (resultCode == RESULT_OK) {
                    // this will start the activity to show all saved flights
                    final Intent intent = IntentFactory.getMyFlightsIntent();
                    startActivity(intent);
                    finish();
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }

    @Override
    public void onClick(FlightData flightData) {
        // in this case, we would show a detail activity with more information about the flight
        final Intent intent = IntentFactory.getFlightDetailIntent(flightData, searchFlightItem, false);
        startActivityForResult(intent, REQUEST_SAVE_FLIGHT_CODE);
    }
}