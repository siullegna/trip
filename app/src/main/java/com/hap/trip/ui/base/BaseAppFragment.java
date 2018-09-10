package com.hap.trip.ui.base;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.hap.trip.viewmodel.TripViewModel;

/**
 * Created by luis on 6/18/18.
 */

public abstract class BaseAppFragment extends Fragment {
    protected TripViewModel tripViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tripViewModel = ViewModelProviders.of(this).get(TripViewModel.class);
    }

    public void showEmptyScreen() {
    }

    public void hideEmptyScreen() {
    }
}
