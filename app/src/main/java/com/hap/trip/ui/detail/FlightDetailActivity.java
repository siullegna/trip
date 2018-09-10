package com.hap.trip.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.hap.trip.R;
import com.hap.trip.model.flight.FlightData;
import com.hap.trip.model.search.SearchFlightItem;
import com.hap.trip.persistence.room.entity.FlightEntity;
import com.hap.trip.ui.base.BaseAppActivity;
import com.hap.trip.ui.result.FlightResultActivity;
import com.hap.trip.util.DateUtil;
import com.hap.trip.util.FlightUtil;
import com.hap.trip.widget.FlightDetailCardView;

import butterknife.BindView;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * This activity will show a single flight element with all the details,
 * will also add a cta to see if we want to save this flight, the cta will be visible/invisible depending
 * on the information coming for this, if the flight was already stored, then we would need to hide it, since it's already
 * our flight, otherwise we would need to show the cta, so we can make it our flight.
 */
public class FlightDetailActivity extends BaseAppActivity {
    public static final String ARG_FLIGHT_DATA_KEY = "com.hap.trip.ui.detail.ARG_FLIGHT_DATA_KEY";
    public static final String ARG_FLIGHT_ENTITY_KEY = "com.hap.trip.ui.detail.ARG_FLIGHT_ENTITY_KEY";
    public static final String ARG_IS_FLIGHT_STORED_KEY = "com.hap.trip.ui.detail.ARG_IS_FLIGHT_STORED_KEY";

    @BindView(R.id.flight_detail_card_view)
    FlightDetailCardView flightDetailCardView;
    @BindView(R.id.btn_save_flight)
    AppCompatButton btnSaveFlight;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private FlightEntity flightEntity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_flight_detail);
        super.onCreate(savedInstanceState);

        final Bundle args = getIntent().getExtras();

        if (args != null) {
            final FlightData flightData = args.getParcelable(ARG_FLIGHT_DATA_KEY);
            final SearchFlightItem searchFlightItem = args.getParcelable(FlightResultActivity.ARG_SEARCH_PARAMS_KEY);
            final boolean isFlightStored = args.getBoolean(ARG_IS_FLIGHT_STORED_KEY);

            if (flightData != null && searchFlightItem != null) {
                flightEntity = FlightEntity.fromDetails(flightData, searchFlightItem);
            } else {
                flightEntity = args.getParcelable(ARG_FLIGHT_ENTITY_KEY);
            }
            flightDetailCardView.bind(flightEntity);

            showHomeButton();
            final ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setTitle(getString(R.string.flight_detail, flightEntity.getInbound()));
                // first we get the value from travelers
                final String travelers = getResources().getQuantityString(R.plurals.flight_result_traveler, flightEntity.getPassengers(), flightEntity.getPassengers());
                actionBar.setSubtitle(getString(R.string.flight_result_date_format, flightEntity.getOutboundDate(), travelers));
            }

            if (isFlightStored) {
                btnSaveFlight.setVisibility(View.GONE);
            } else {
                btnSaveFlight.setVisibility(View.VISIBLE);
                btnSaveFlight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        compositeDisposable.add(tripViewModel.insertFlights(flightEntity)
                                .subscribeOn(Schedulers.io())
                                .subscribe(new Action() {
                                    @Override
                                    public void run() throws Exception {

                                    }
                                }, new Consumer<Throwable>() {
                                    @Override
                                    public void accept(Throwable throwable) {

                                    }
                                }));
                        final Intent result = new Intent();
                        setResult(result);
                    }
                });
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        compositeDisposable.clear();
    }
}