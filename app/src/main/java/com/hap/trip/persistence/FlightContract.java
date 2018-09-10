package com.hap.trip.persistence;

import android.net.Uri;
import android.provider.BaseColumns;

import com.hap.trip.R;
import com.hap.trip.TripApplication;

public class FlightContract {
    private FlightContract() {

    }

    public static final String CONTENT_AUTHORITY = "com.hap.trip";
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_FLIGHTS = "flights";

    public static final class FlightEntity implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_FLIGHTS)
                .build();

        public static final String TBL_FLIGHT = "flight";

        public static final String COLUMN_OUTBOUND = "outbound";
        public static final String COLUMN_OUTBOUND_ROUTE = "outbound_route";
        public static final String COLUMN_OUTBOUND_DURATION = "outbound_duration";
        public static final String COLUMN_OUTBOUND_DATE = "outbound_date";
        public static final String COLUMN_OUTBOUND_FLIGHTS = "outbound_flights";
        public static final String COLUMN_INBOUND = "inbound";
        public static final String COLUMN_INBOUND_ROUTE = "inbound_route";
        public static final String COLUMN_INBOUND_DURATION = "inbound_duration";
        public static final String COLUMN_INBOUND_DATE = "inbound_date";
        public static final String COLUMN_INBOUND_FLIGHTS = "inbound_flights";
        public static final String COLUMN_PASSENGERS = "passengers";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_CREATION_DATE = "creation_date";

        public static Uri getContentUryByFlightId(final String flightId) {
            return BASE_CONTENT_URI.buildUpon()
                    .appendPath(PATH_FLIGHTS)
                    .appendPath(flightId)
                    .build();
        }
    }
}
