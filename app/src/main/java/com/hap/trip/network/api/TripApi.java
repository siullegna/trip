package com.hap.trip.network.api;

import com.hap.trip.model.AirportItem;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by luis on 6/16/18.
 */

public interface TripApi {
    @GET("airports/autocomplete")
    Observable<ArrayList<AirportItem>> searchAirport(@Query("apikey") final String apiKey, @Query("term") final String airportTerm);
}