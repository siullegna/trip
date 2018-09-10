package com.hap.trip.dagger.component;

import com.hap.trip.adapter.pager.BasePagerAdapter;
import com.hap.trip.viewmodel.TripViewModel;

/**
 * Created by luis on 6/16/18.
 */

public interface AppGraph {
    void inject(final BasePagerAdapter basePagerAdapter);

    void inject(final TripViewModel tripViewModel);
}
