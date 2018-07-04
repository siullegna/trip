package com.hap.trip.util;

import android.content.Intent;
import android.os.Bundle;

import com.hap.trip.TripApplication;
import com.hap.trip.ui.airport.AirportSearchActivity;
import com.hap.trip.ui.date.DatePickerActivity;
import com.hap.trip.ui.flight.FlightActivity;
import com.hap.trip.ui.passenger.SelectPassengerActivity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by luis on 6/23/18.
 */

public class IntentFactory {
    private static Intent getIntent(@Nonnull final Class targetClass, @Nullable final Bundle args) {
        final Intent intent = new Intent(TripApplication.getInstance(), targetClass);
        if (args != null) {
            intent.putExtras(args);
        }
        return intent;
    }

    private static Intent getIntent(final Class targetClass) {
        return getIntent(targetClass, null);
    }

    public static Intent getFlightIntent() {
        return getIntent(FlightActivity.class);
    }

    public static Intent getAirportSearchIntent(final String searchTitle) {
        final Bundle args = new Bundle();
        args.putString(AirportSearchActivity.ARG_SEARCH_TITLE_KEY, searchTitle);
        return getIntent(AirportSearchActivity.class, args);
    }

    public static Intent getDateIntent(final long minDate) {
        final Bundle args = new Bundle();
        args.putLong(DatePickerActivity.ARG_MIN_DATE_KEY, minDate);
        return getIntent(DatePickerActivity.class, args);
    }

    public static Intent getPassengerIntent(final int adulCount, final int childrenCount, final int infantCount) {
        final Bundle args = new Bundle();
        args.putInt(SelectPassengerActivity.ARG_ADULT_KEY, adulCount);
        args.putInt(SelectPassengerActivity.ARG_CHILDREN_KEY, childrenCount);
        args.putInt(SelectPassengerActivity.ARG_INFANT_KEY, infantCount);
        return getIntent(SelectPassengerActivity.class, args);
    }
}
