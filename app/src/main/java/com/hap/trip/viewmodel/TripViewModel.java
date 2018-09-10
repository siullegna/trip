package com.hap.trip.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.hap.trip.TripApplication;
import com.hap.trip.model.AirportItemResponse;
import com.hap.trip.model.flight.FlightItemResponse;
import com.hap.trip.model.location.LocationAirportItemResponse;
import com.hap.trip.model.search.SearchFlightItem;
import com.hap.trip.network.service.TripRestServiceImpl;
import com.hap.trip.persistence.room.entity.FlightEntity;
import com.hap.trip.persistence.room.source.FlightQueryDataSourceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by luis on 6/25/18.
 */

public class TripViewModel extends ViewModel implements TripViewModelContract {
    @Inject
    protected TripRestServiceImpl tripRestService;
    @Inject
    protected FlightQueryDataSourceImpl flightQueryDataSource;

    public TripViewModel() {
        TripApplication.getInstance().inject(this);
    }

    @Override
    public LiveData<AirportItemResponse> searchAirport(String airportTerm) {
        return tripRestService.searchAirport(airportTerm);
    }

    @Override
    public LiveData<LocationAirportItemResponse> searchAirportByLocation(final double latitude, final double longitude) {
        return tripRestService.searchAirportByLocation(latitude, longitude);
    }

    @Override
    public LiveData<FlightItemResponse> searchFlights(@Nonnull final SearchFlightItem searchFlightItem) {
        return tripRestService.searchFlights(searchFlightItem);
    }

    public Completable insertFlights(final FlightEntity... flightEntities) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() {
                flightQueryDataSource.insertFlights(flightEntities);
            }
        });
    }

    public List<FlightEntity> selectAllflights() {
        return flightQueryDataSource.selectAllList();
    }
}