package com.hap.trip.network.service;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.hap.trip.BuildConfig;
import com.hap.trip.model.AirportItem;
import com.hap.trip.model.AirportItemResponse;
import com.hap.trip.model.Source;
import com.hap.trip.model.flight.FlightItem;
import com.hap.trip.model.flight.FlightItemResponse;
import com.hap.trip.model.location.LocationAirportItem;
import com.hap.trip.model.location.LocationAirportItemResponse;
import com.hap.trip.model.search.SearchFlightItem;
import com.hap.trip.network.api.TripApi;
import com.hap.trip.util.AirportUtil;

import java.util.ArrayList;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by luis on 6/16/18.
 */
@SuppressLint("CheckResult")
public class TripRestServiceImpl implements TripRestServiceContract {
    private final TripApi tripApi;

    public TripRestServiceImpl(TripApi tripApi) {
        this.tripApi = tripApi;
    }

    @Override
    public MutableLiveData<AirportItemResponse> searchAirport(final String airportTerm) {
        final MutableLiveData<AirportItemResponse> liveData = new MutableLiveData<>();

        tripApi.searchAirport(BuildConfig.TripApiKey, airportTerm)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<ArrayList<AirportItem>>() {
                    @Override
                    public void accept(ArrayList<AirportItem> airportItems) {
                        if (airportItems == null) {
                            airportItems = new ArrayList<>();
                        }
                        final AirportItemResponse airportItemResponse = new AirportItemResponse();
                        airportItemResponse.setAirportItemArrayList(airportItems);
                        airportItemResponse.setSource(Source.NETWORK);
                        liveData.setValue(airportItemResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        final AirportItemResponse airportItemResponse = new AirportItemResponse();
                        airportItemResponse.setError(throwable);
                        airportItemResponse.setSource(Source.NETWORK);
                        liveData.setValue(airportItemResponse);
                    }
                });

        return liveData;
    }

    @Override
    public LiveData<LocationAirportItemResponse> searchAirportByLocation(final double latitude, final double longitude) {
        final MutableLiveData<LocationAirportItemResponse> liveData = new MutableLiveData<>();

        tripApi.searchAirportByLocation(BuildConfig.TripApiKey, latitude, longitude)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<ArrayList<LocationAirportItem>>() {
                    @Override
                    public void accept(ArrayList<LocationAirportItem> locationAirportItems) {
                        if (locationAirportItems == null) {
                            locationAirportItems = new ArrayList<>();
                        }

                        final LocationAirportItemResponse locationAirportItemResponse = new LocationAirportItemResponse();
                        locationAirportItemResponse.setAirportItemArrayList(locationAirportItems);
                        locationAirportItemResponse.setSource(Source.NETWORK);
                        liveData.setValue(locationAirportItemResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        final LocationAirportItemResponse locationAirportItemResponse = new LocationAirportItemResponse();
                        locationAirportItemResponse.setSource(Source.NETWORK);
                        locationAirportItemResponse.setError(throwable);
                        liveData.setValue(locationAirportItemResponse);
                    }
                });

        return liveData;
    }

    @Override
    public LiveData<FlightItemResponse> searchFlights(@Nonnull final SearchFlightItem searchFlightItem) {
        final MutableLiveData<FlightItemResponse> liveData = new MutableLiveData<>();

        final String url = AirportUtil.getFlightUrl(searchFlightItem);

        tripApi.searchFlights(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<FlightItem>() {
                    @Override
                    public void accept(FlightItem flightItem) {
                        if (flightItem == null) {
                            flightItem = new FlightItem();
                        }

                        final FlightItemResponse flightItemResponse = new FlightItemResponse();
                        flightItemResponse.setFlightItem(flightItem);
                        flightItemResponse.setSource(Source.NETWORK);
                        liveData.setValue(flightItemResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        final FlightItemResponse flightItemResponse = new FlightItemResponse();
                        flightItemResponse.setSource(Source.NETWORK);
                        flightItemResponse.setError(throwable);
                        liveData.setValue(flightItemResponse);
                    }
                });

        return liveData;
    }
}
