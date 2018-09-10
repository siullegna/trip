package com.hap.trip.util;

import com.hap.trip.model.flight.FlightData;

import java.util.Comparator;

public class SortFlightByPrice implements Comparator<FlightData> {
    @Override
    public int compare(FlightData o1, FlightData o2) {
        try {
            final int price1 = Integer.parseInt(o1.getFare().getTotalPrice());
            final int price2 = Integer.parseInt(o2.getFare().getTotalPrice());

            return price1 - price2;
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
