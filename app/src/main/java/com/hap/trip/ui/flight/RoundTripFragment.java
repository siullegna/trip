package com.hap.trip.ui.flight;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hap.trip.Config;
import com.hap.trip.R;
import com.hap.trip.ui.date.DatePickerActivity;
import com.hap.trip.util.IntentFactory;
import com.hap.trip.widget.InputTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by luis on 6/23/18.
 */

public class RoundTripFragment extends BaseTripFragment {
    private static final int REQUEST_RETURNING_KEY = 1005;

    public static RoundTripFragment getInstance() {
        return new RoundTripFragment();
    }

    @BindView(R.id.input_returning)
    protected InputTextView inputReturning;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_trip, container, false);
        ButterKnife.bind(this, view);

        inputReturning.setVisibility(View.VISIBLE);
        inputReturning.setEnabled(false);
        setupViews(view);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_DEPART_KEY) {
            if (resultCode == Activity.RESULT_OK) {
                inputReturning.setEnabled(true);
                inputReturning.resetValues();
            }
        }

        switch (requestCode) {
            case REQUEST_RETURNING_KEY:
                if (resultCode == Activity.RESULT_OK) {
                    final long date = data.getLongExtra(DatePickerActivity.RESULT_SELECTED_DATE_KEY, System.currentTimeMillis());
                    inputReturning.resetValues();
                    inputReturning.setDate(date);
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void setupViews(View view) {
        super.setupViews(view);

        inputReturning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent departIntent = IntentFactory.getDateIntent(inputDepart.getDate());
                startActivityForResult(departIntent, REQUEST_RETURNING_KEY);
            }
        });
    }

    @Override
    protected void resetValues() {
        super.resetValues();

        inputReturning.resetValues();
    }

    @Override
    protected boolean isValid() {
        boolean isValid = super.isValid();

        if (!inputReturning.isValidInput()) {
            isValid = false;
            inputReturning.setError(getString(R.string.flight_returning_error));
        }

        return isValid;
    }

    @Override
    public Long getReturningDate() {
        return inputReturning.getDate();
    }
}