package com.hap.trip.network.service;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.hap.trip.BuildConfig;
import com.hap.trip.model.AirportItem;
import com.hap.trip.network.api.TripApi;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by luis on 6/16/18.
 */

public class TripRestServiceImpl implements TripRestServiceContract {
    private final TripApi tripApi;

    public TripRestServiceImpl(TripApi tripApi) {
        this.tripApi = tripApi;
    }

    @Override
    public LiveData<ArrayList<AirportItem>> searchAirport(final String airportTerm) {
        final MutableLiveData<ArrayList<AirportItem>> liveData = new MutableLiveData<>();

        tripApi.searchAirport(BuildConfig.ApiKey, airportTerm)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<ArrayList<AirportItem>>() {
                    @Override
                    public void accept(ArrayList<AirportItem> airportItems) throws Exception {
                        if (airportItems == null) {
                            airportItems = new ArrayList<>();
                        }
                        liveData.setValue(airportItems);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        liveData.setValue(new ArrayList<AirportItem>());
                    }
                });

        return liveData;
    }
}
