package com.hap.trip.ui.airport;

import android.content.Context;

import com.hap.trip.model.location.LocationAirportItem;
import com.hap.trip.ui.base.BaseAppFragment;

public abstract class BaseAirportFragment extends BaseAppFragment {
    protected OnAirportListener onAirportListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAirportListener) {
            onAirportListener = (OnAirportListener) context;
        } else {
            throw new RuntimeException("Parent activity must implement {OnAirportListener}");
        }
    }

    public interface OnAirportListener {
        void onOriginSelected(final LocationAirportItem origin);

        void onDestinationSelected(final LocationAirportItem destination);

        void onAirportLoadeError();
    }
}
