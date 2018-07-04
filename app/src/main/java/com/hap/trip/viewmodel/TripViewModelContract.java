package com.hap.trip.viewmodel;

import android.arch.lifecycle.LiveData;

import com.hap.trip.model.AirportItem;

import java.util.ArrayList;

/**
 * Created by luis on 6/25/18.
 */

interface TripViewModelContract {
    LiveData<ArrayList<AirportItem>> searchAirport(final String airportTerm);
}