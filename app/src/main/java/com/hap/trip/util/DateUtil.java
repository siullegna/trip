package com.hap.trip.util;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.hap.trip.R;
import com.hap.trip.TripApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DateUtil {
    private static final String DATE_SEARCH_FORMAT = "YYYY-MM-dd";
    private static final String DATE_FLIGHT_FORMAT = "YYYY-MM-dd'T'HH:mm";
    private static final String DATE_SELECTED_FORMAT = "EEE, MM dd";

    /**
     * This is going to return the date in the format {YYY-MM-dd}, which will be helping us to create the
     * search of the flight calling the api
     * This is something that is not going to be displayed to the user
     *
     * @param date - the date in milliseconds
     * @return the date in the format {YYY-MM-dd}
     */
    public static String getFormattedSearchDate(@Nullable final Long date) {
        if (date == null) {
            return null;
        }

        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_SEARCH_FORMAT, Locale.US);
        return simpleDateFormat.format(new Date(date));
    }

    public static String getTimeDifference(final String departAt, final String arriveAt) {
        if (TextUtils.isEmpty(departAt) || TextUtils.isEmpty(arriveAt)) {
            return null;
        }

        try {
            final Date departDate = new SimpleDateFormat(DATE_FLIGHT_FORMAT, Locale.US).parse(departAt);
            final Date arriveDate = new SimpleDateFormat(DATE_FLIGHT_FORMAT, Locale.US).parse(arriveAt);

            final long diffInMillis = arriveDate.getTime() - departDate.getTime();

            int minutes = (int) ((diffInMillis / (1000 * 60)) % 60);
            int hours = (int) ((diffInMillis / (1000 * 60 * 60)) % 24);

            return TripApplication.getInstance().getResources().getString(R.string.flight_result_time_format_3, hours, minutes);
        } catch (ParseException e) {
            return null;
        }
    }

    private static Calendar getCalendarFromDate(@Nullable final String date) {
        if (date == null) {
            return null;
        }

        try {
            final Date date1 = new SimpleDateFormat(DATE_FLIGHT_FORMAT, Locale.US).parse(date);
            final Calendar calendar = Calendar.getInstance();
            calendar.setTime(date1);

            return calendar;
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * This is receiving a date from the api response, this date will be in the format {YYY-MM-dd'T'HH:mm}
     * and we will return only the time in the format {HH:mm}
     *
     * @param date - the date in the format {YYY-MM-dd'T'HH:mm}
     * @return the time in the format {HH:mm}
     */
    public static String getFormattedTime(@Nullable final String date) {
        if (date == null) {
            return null;
        }

        final Calendar calendar = DateUtil.getCalendarFromDate(date);
        if (calendar == null) {
            return null;
        }

        final String hour = calendar.get(Calendar.HOUR_OF_DAY) > 10
                ? String.valueOf(calendar.get(Calendar.HOUR_OF_DAY))
                : "0".concat(String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)));

        final String minute = calendar.get(Calendar.MINUTE) > 10
                ? String.valueOf(calendar.get(Calendar.MINUTE))
                : "0".concat(String.valueOf(calendar.get(Calendar.MINUTE)));

        return TripApplication.getInstance().getResources().getString(R.string.flight_result_time_format, hour, minute);
    }

    /**
     * This is going to receive a date in milliseconds, and will return a date in the format {EEE, MM dd}
     * which will be displayed to the user
     *
     * @param date - the date in milliseconds
     * @return the date in the format {EEE, EE dd}
     */
    public static String getSelectedDateFormatted(final long date) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_SELECTED_FORMAT, Locale.getDefault());
        return dateFormat.format(new Date(date));
    }


}
