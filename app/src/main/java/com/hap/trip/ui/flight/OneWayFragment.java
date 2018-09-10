package com.hap.trip.ui.flight;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hap.trip.Config;
import com.hap.trip.R;
import com.hap.trip.widget.InputTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by luis on 6/23/18.
 */

public class OneWayFragment extends BaseTripFragment {
    public static OneWayFragment getInstance() {
        return new OneWayFragment();
    }

    @BindView(R.id.input_returning)
    protected InputTextView inputReturning;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_trip, container, false);
        ButterKnife.bind(this, view);

        inputReturning.setVisibility(View.GONE);
        inputReturning.setEnabled(false);
        setupViews(view);

        setupViews(view);

        return view;
    }

    @Override
    public Long getReturningDate() {
        return null;
    }
}
