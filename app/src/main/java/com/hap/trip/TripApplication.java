package com.hap.trip;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;
import com.hap.trip.adapter.FlightPagerAdapter;
import com.hap.trip.dagger.component.AppGraph;
import com.hap.trip.dagger.component.DaggerTripAppComponent;
import com.hap.trip.dagger.component.TripAppComponent;
import com.hap.trip.dagger.module.ContextModule;
import com.hap.trip.dagger.module.NetworkModule;
import com.hap.trip.ui.base.BaseAppActivity;
import com.hap.trip.ui.base.BaseAppFragment;
import com.hap.trip.viewmodel.TripViewModel;

/**
 * Created by luis on 6/16/18.
 */

public class TripApplication extends Application implements AppGraph {
    private static TripApplication INSTANCE;
    private TripAppComponent tripAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        TripApplication.INSTANCE = this;

        tripAppComponent = DaggerTripAppComponent.builder()
                .networkModule(new NetworkModule())
                .contextModule(new ContextModule(this))
                .build();

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }

        Fresco.initialize(this);
    }

    public static TripApplication getInstance() {
        return INSTANCE;
    }

    @Override
    public void inject(FlightPagerAdapter flightPagerAdapter) {
        tripAppComponent.inject(flightPagerAdapter);
    }

    @Override
    public void inject(TripViewModel tripViewModel) {
        tripAppComponent.inject(tripViewModel);
    }
}
