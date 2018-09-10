package com.hap.trip.network.api;

import com.hap.trip.model.AirportItem;
import com.hap.trip.model.flight.FlightItem;
import com.hap.trip.model.location.LocationAirportItem;

import java.util.ArrayList;

import javax.annotation.Nonnull;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by luis on 6/16/18.
 */

public interface TripApi {
    @GET("airports/autocomplete")
    Observable<ArrayList<AirportItem>> searchAirport(@Query("apikey") @Nonnull final String apiKey,
                                                     @Query("term") @Nonnull final String airportTerm);

    @GET("airports/nearest-relevant")
    Observable<ArrayList<LocationAirportItem>> searchAirportByLocation(@Query("apikey") @Nonnull final String apiKey,
                                                                       @Query("latitude") final double latitude,
                                                                       @Query("longitude") final double longitude);

    @GET
    Observable<FlightItem> searchFlights(@Url String url);
}