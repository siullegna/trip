package com.hap.trip.widget;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.hap.trip.R;

public class DialogInfoFragment extends DialogFragment {
    private static final String ARG_AIRPORT_NAME_KEY = "com.hap.trip.widget.ARG_AIRPORT_NAME_KEY";
    private FlightTypeView.OnFlightListener onFlightListener;

    public static DialogInfoFragment getInstance(final String airportName, final FlightTypeView.OnFlightListener onFlightListener) {
        final DialogInfoFragment dialogInfoFragment = new DialogInfoFragment();

        dialogInfoFragment.onFlightListener = onFlightListener;

        final Bundle args = new Bundle();
        args.putString(ARG_AIRPORT_NAME_KEY, airportName);
        dialogInfoFragment.setArguments(args);

        return dialogInfoFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (getArguments() == null) {
            throw new RuntimeException("getArguments should not be null");
        }

        final String airportName = getArguments().getString(ARG_AIRPORT_NAME_KEY);

        if (airportName == null) {
            throw new RuntimeException("locationAirportItem should not be null");
        }

        if (getActivity() == null) {
            return super.onCreateDialog(savedInstanceState);
        }

        final FlightTypeView flightTypeView = new FlightTypeView(getContext());
        flightTypeView.setupListener(onFlightListener);

        return new AlertDialog.Builder(getActivity())
                .setTitle(airportName)
                .setView(flightTypeView)
                .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();
    }
}
