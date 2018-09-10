package com.hap.trip.viewmodel;

import android.arch.lifecycle.LiveData;

import com.hap.trip.model.AirportItemResponse;
import com.hap.trip.model.flight.FlightItemResponse;
import com.hap.trip.model.location.LocationAirportItemResponse;
import com.hap.trip.model.search.SearchFlightItem;

import javax.annotation.Nonnull;

/**
 * Created by luis on 6/25/18.
 */

interface TripViewModelContract {
    LiveData<AirportItemResponse> searchAirport(final String airportTerm);

    LiveData<LocationAirportItemResponse> searchAirportByLocation(final double latitude, final double longitude);

    LiveData<FlightItemResponse> searchFlights(@Nonnull final SearchFlightItem searchFlightItem);
}