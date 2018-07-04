package com.hap.trip.dagger.component;

import com.hap.trip.adapter.FlightPagerAdapter;
import com.hap.trip.ui.base.BaseAppActivity;
import com.hap.trip.ui.base.BaseAppFragment;
import com.hap.trip.viewmodel.TripViewModel;

/**
 * Created by luis on 6/16/18.
 */

public interface AppGraph {
    void inject(final FlightPagerAdapter flightPagerAdapter);

    void inject(final TripViewModel tripViewModel);
}
