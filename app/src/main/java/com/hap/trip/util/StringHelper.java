package com.hap.trip.util;

import android.content.res.Resources;

import com.hap.trip.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by luis on 6/26/18.
 */

public class StringHelper {
    private static final String dateFormatPattern = "EEE, MM dd";

    public static String getFormattedDate(final long date) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatPattern, Locale.getDefault());
        return dateFormat.format(new Date(date));
    }

    public static String getFormattedPassenger(final Resources resources, final int adultCount, final int childrenCount, final int infantCount) {
        return resources.getString(R.string.flight_passenger_format, adultCount, childrenCount, infantCount);
    }
}