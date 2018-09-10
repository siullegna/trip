package com.hap.trip.model;

import java.util.ArrayList;

public class AirportItemResponse {
    private ArrayList<AirportItem> airportItemArrayList;
    private Throwable error;
    private Source source;

    public ArrayList<AirportItem> getAirportItemArrayList() {
        return airportItemArrayList;
    }

    public void setAirportItemArrayList(ArrayList<AirportItem> airportItemArrayList) {
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
