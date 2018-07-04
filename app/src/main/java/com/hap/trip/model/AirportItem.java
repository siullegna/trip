package com.hap.trip.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by luis on 6/16/18.
 */

public class AirportItem {
    @SerializedName("value")
    private String airportCode;
    @SerializedName("label")
    private String airportName;

    public String getAirportCode() {
        return airportCode;
    }

    public String getAirportName() {
        return airportName;
    }
}
