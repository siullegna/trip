package com.hap.trip.network.service;

import android.arch.lifecycle.LiveData;

import com.hap.trip.model.AirportItem;

import java.util.ArrayList;

/**
 * Created by luis on 6/16/18.
 */

public interface TripRestServiceContract {
    LiveData<ArrayList<AirportItem>> searchAirport(final String airportTerm);
}
