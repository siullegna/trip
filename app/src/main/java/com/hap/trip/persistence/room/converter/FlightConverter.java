package com.hap.trip.persistence.room.converter;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hap.trip.persistence.room.entity.FlightDetail;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class FlightConverter {
    private static final Gson gson = new Gson();

    @TypeConverter
    public static ArrayList<FlightDetail> fromString(final String string) {
        final Type listType = new TypeToken<ArrayList<FlightDetail>>() {

        }.getType();
        return gson.fromJson(string, listType);
    }

    @TypeConverter
    public static String fromArrayList(final ArrayList<FlightDetail> flightDetails) {
        return gson.toJson(flightDetails);
    }
}
