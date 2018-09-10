package com.hap.trip.util;

import com.hap.trip.model.flight.FlightData;

import java.util.Comparator;

public class SortFlightByAirline implements Comparator<FlightData> {
    @Override
    public int compare(FlightData o1, FlightData o2) {
        final String airport1 = o1.getItinerarie().getOutbound().getFlights().get(0).getAircraft();
        final String airport2 = o2.getItinerarie().getOutbound().getFlights().get(0).getAircraft();

        return airport1.compareToIgnoreCase(airport2);
    }
}
