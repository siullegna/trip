package com.hap.trip.persistence.room.converter;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class DateConverter {
    @TypeConverter
    public static Date fromLong(final Long publishedDate) {
        return new Date(publishedDate);
    }

    @TypeConverter
    public static Long fromDate(final Date publishedDate) {
        if (publishedDate == null) {
            return null;
        }
        return publishedDate.getTime();
    }
}
