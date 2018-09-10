package com.hap.trip.model.flight;

import com.hap.trip.model.Source;

public class FlightItemResponse {
    private FlightItem flightItem;
    private Throwable error;
    private Source source;

    public FlightItem getFlightItem() {
        return flightItem;
    }

    public void setFlightItem(FlightItem flightItem) {
        this.flightItem = flightItem;
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
