package com.hap.trip.model.location;

import com.hap.trip.model.Source;

import java.util.ArrayList;

public class LocationAirportItemResponse {
    private ArrayList<LocationAirportItem> airportItemArrayList;
    private Throwable error;
    private Source source;

    public ArrayList<LocationAirportItem> getAirportItemArrayList() {
        return airportItemArrayList;
    }

    public void setAirportItemArrayList(ArrayList<LocationAirportItem> airportItemArrayList) {
        this.airportItemArrayList = airportItemArrayList;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }
}
