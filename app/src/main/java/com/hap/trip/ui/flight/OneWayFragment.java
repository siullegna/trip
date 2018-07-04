package com.hap.trip.ui.flight;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hap.trip.R;

/**
 * Created by luis on 6/23/18.
 */

public class OneWayFragment extends BaseTripFragment {
    public static OneWayFragment getInstance() {
        return new OneWayFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_trip, container, false);

        setupViews(view);

        return view;
    }
}
