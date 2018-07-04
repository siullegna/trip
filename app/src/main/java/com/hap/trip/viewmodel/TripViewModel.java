package com.hap.trip.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.hap.trip.TripApplication;
import com.hap.trip.model.AirportItem;
import com.hap.trip.network.service.TripRestServiceImpl;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by luis on 6/25/18.
 */

public class TripViewModel extends ViewModel implements TripViewModelContract {
    @Inject
    protected TripRestServiceImpl tripRestService;

    public TripViewModel() {
        TripApplication.getInstance().inject(this);
    }

    @Override
    public LiveData<ArrayList<AirportItem>> searchAirport(String airportTerm) {
        return tripRestService.searchAirport(airportTerm);
    }
}