package com.hap.trip.ui.airport;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hap.trip.R;
import com.hap.trip.adapter.AirportAdapter;
import com.hap.trip.model.AirportItem;
import com.hap.trip.model.AirportItemResponse;
import com.hap.trip.model.location.LocationAirportItem;
import com.hap.trip.util.DeviceUtil;
import com.hap.trip.util.OnTextChangeListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AirportListSearchFragment extends BaseAirportFragment implements AirportAdapter.OnItemClickListener {
    private static final String ARG_IS_ORIGIN_KEY = "com.hap.trip.ui.airport.ARG_IS_ORIGIN_KEY";
    private static final String ARG_HINT_KEY = "com.hap.trip.ui.airport.ARG_HINT_KEY";
    private static final String ARG_ICON_KEY = "com.hap.trip.ui.airport.ARG_ICON_KEY";

    @BindView(R.id.et_flight_search)
    AppCompatEditText etFlightSearch;
    @BindView(R.id.rv_flights)
    RecyclerView rvFlights;

    private boolean isOrigin = false;

    private AirportAdapter airportAdapter;

    public static AirportListSearchFragment getInstance(final boolean isOrigin, final String hint, final int icon) {
        final AirportListSearchFragment airportListSearchFragment = new AirportListSearchFragment();

        final Bundle args = new Bundle();
        args.putBoolean(ARG_IS_ORIGIN_KEY, isOrigin);
        args.putString(ARG_HINT_KEY, hint);
        args.putInt(ARG_ICON_KEY, icon);
        airportListSearchFragment.setArguments(args);

        return airportListSearchFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_airport_list_search, container, false);
        ButterKnife.bind(this, view);

        if (getArguments() == null) {
            throw new RuntimeException("getArguments should not be null");
        }

        isOrigin = getArguments().getBoolean(ARG_IS_ORIGIN_KEY);
        final String hint = getArguments().getString(ARG_HINT_KEY);
        final int icon = getArguments().getInt(ARG_ICON_KEY);

        etFlightSearch.setHint(hint);
        etFlightSearch.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0);
        if (getContext() != null) {
            DeviceUtil.showKeyboard(etFlightSearch, getContext());
        }

        rvFlights.setHasFixedSize(false);
        rvFlights.setLayoutManager(new LinearLayoutManager(getContext()));

        airportAdapter = new AirportAdapter(this);
        rvFlights.setAdapter(airportAdapter);

        etFlightSearch.addTextChangedListener(onTextWatcher);

        return view;
    }

    @Override
    public void onItemClick(LocationAirportItem locationAirportItem) {
        if (isOrigin) {
            onAirportListener.onOriginSelected(locationAirportItem);
        } else {
            onAirportListener.onDestinationSelected(locationAirportItem);
        }
    }

    private void textChanged(final String airportTerm) {
        tripViewModel.searchAirport(airportTerm)
                .observe(this, new Observer<AirportItemResponse>() {
                    @Override
                    public void onChanged(@Nullable AirportItemResponse airportItemResponse) {
                        if (airportItemResponse == null) {
                            return;
                        }

                        if (airportItemResponse.getError() != null) {
                            onAirportListener.onAirportLoadeError();
                        } else if (airportItemResponse.getAirportItemArrayList() != null) {
                            final ArrayList<LocationAirportItem> locationAirportItems = new ArrayList<>();

                            for (final AirportItem currentAirport : airportItemResponse.getAirportItemArrayList()) {
                                final LocationAirportItem locationAirportItem = LocationAirportItem.fromAirportItem(currentAirport);
                                locationAirportItems.add(locationAirportItem);
                            }

                            airportAdapter.addAll(locationAirportItems);
                        }
                    }
                });
    }

    private final OnTextChangeListener onTextWatcher = new OnTextChangeListener() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s != null) {
                textChanged(s.toString());
            }
        }
    };
}
