package com.hap.trip.util;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.hap.trip.TripApplication;
import com.hap.trip.model.filter.FilterItem;
import com.hap.trip.model.flight.FlightData;
import com.hap.trip.model.flight.FlightItem;
import com.hap.trip.model.location.LocationAirportItem;
import com.hap.trip.model.search.SearchFlightItem;
import com.hap.trip.persistence.room.entity.FlightEntity;
import com.hap.trip.service.TripWidgetService;
import com.hap.trip.ui.airport.BaseAirportActivity;
import com.hap.trip.ui.airport.DestinationAirportActivity;
import com.hap.trip.ui.airport.OriginAirportActivity;
import com.hap.trip.ui.date.DatePickerActivity;
import com.hap.trip.ui.detail.FlightDetailActivity;
import com.hap.trip.ui.detail.MyFlightsActivity;
import com.hap.trip.ui.flight.FlightActivity;
import com.hap.trip.ui.passenger.SelectPassengerActivity;
import com.hap.trip.ui.result.FlightFilterActivity;
import com.hap.trip.ui.result.FlightResultActivity;

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

    public static Intent getOriginSearchIntent(final LocationAirportItem origin) {
        final Bundle args = new Bundle();
        args.putParcelable(BaseAirportActivity.ARG_SELECTED_FLIGHT_KEY, origin);
        return getIntent(OriginAirportActivity.class, args);
    }

    public static Intent getDestinationSearchIntent(final LocationAirportItem destination) {
        final Bundle args = new Bundle();
        args.putParcelable(BaseAirportActivity.ARG_SELECTED_FLIGHT_KEY, destination);
        return getIntent(DestinationAirportActivity.class, args);
    }

    public static Intent getDateIntent(final long minDate) {
        final Bundle args = new Bundle();
        args.putLong(DatePickerActivity.ARG_MIN_DATE_KEY, minDate);
        return getIntent(DatePickerActivity.class, args);
    }

    public static Intent getPassengerIntent(final int adultCount, final int childrenCount, final int infantCount) {
        final Bundle args = new Bundle();
        args.putInt(SelectPassengerActivity.ARG_ADULT_KEY, adultCount);
        args.putInt(SelectPassengerActivity.ARG_CHILDREN_KEY, childrenCount);
        args.putInt(SelectPassengerActivity.ARG_INFANT_KEY, infantCount);
        return getIntent(SelectPassengerActivity.class, args);
    }

    public static Intent getFlightResultIntent(final FlightItem flightItem, final SearchFlightItem searchFlightItem) {
        final Bundle args = new Bundle();
        args.putParcelable(FlightResultActivity.ARG_FLIGHTS_FOUND_KEY, flightItem);
        args.putParcelable(FlightResultActivity.ARG_SEARCH_PARAMS_KEY, searchFlightItem);
        return getIntent(FlightResultActivity.class, args);
    }

    public static Intent getFlightFilterIntent(final FilterItem filterItem) {
        final Bundle args = new Bundle();
        args.putParcelable(FlightFilterActivity.ARG_FILTER_ITEM_KEY, filterItem);
        return getIntent(FlightFilterActivity.class, args);
    }

    public static Intent getFlightDetailIntent(final FlightData flightData, final SearchFlightItem searchFlightItem, final boolean isStoredFlight) {
        final Bundle args = new Bundle();
        args.putParcelable(FlightDetailActivity.ARG_FLIGHT_DATA_KEY, flightData);
        args.putParcelable(FlightResultActivity.ARG_SEARCH_PARAMS_KEY, searchFlightItem);
        args.putBoolean(FlightDetailActivity.ARG_IS_FLIGHT_STORED_KEY, isStoredFlight);
        return getIntent(FlightDetailActivity.class, args);
    }

    public static Intent getFlightDetailIntent(final FlightEntity flightEntity, final boolean isFlightStored) {
        final Bundle args = new Bundle();
        args.putParcelable(FlightDetailActivity.ARG_FLIGHT_ENTITY_KEY, flightEntity);
        args.putBoolean(FlightDetailActivity.ARG_IS_FLIGHT_STORED_KEY, isFlightStored);
        return getIntent(FlightDetailActivity.class, args);
    }

    public static Intent getFlightDetailIntent() {
        return getIntent(FlightDetailActivity.class);
    }

    public static Intent getMyFlightsIntent() {
        return getIntent(MyFlightsActivity.class);
    }

    public static Intent getWidgetServiceIntent(final int appWidgetId) {
        final Intent intent = getIntent(TripWidgetService.class);

        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));

        return intent;
    }
}
