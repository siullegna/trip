package com.hap.trip.util;

import com.hap.trip.BuildConfig;
import com.hap.trip.model.search.SearchFlightItem;

import javax.annotation.Nonnull;

public class AirportUtil {
    public static String getFlightUrl(@Nonnull final SearchFlightItem searchFlightItem) {
        final StringBuilder url = new StringBuilder();
        final String path = "flights/low-fare-search";

        // adding path
        url.append(path);

        // adding apiKey
        url.append("?apikey=");
        url.append(BuildConfig.TripApiKey);

        // adding origin
        url.append("&origin=");
        url.append(searchFlightItem.getOriginCode());

        // adding destination
        url.append("&destination=");
        url.append(searchFlightItem.getDestinationCode());

        // adding departure_date
        url.append("&departure_date=");
        url.append(DateUtil.getFormattedSearchDate(searchFlightItem.getDepartureDate()));

        // adding return_date
        if (searchFlightItem.getReturnDate() != null) {
            url.append("&return_date=");
            url.append(DateUtil.getFormattedSearchDate(searchFlightItem.getReturnDate()));
        }

        if (searchFlightItem.getAdults() > 0) {
            // adding adults
            url.append("&adults=");
            url.append(searchFlightItem.getAdults());
        }

        if (searchFlightItem.getChildren() > 0) {
            // adding children
            url.append("&children=");
            url.append(searchFlightItem.getChildren());
        }

        if (searchFlightItem.getInfants() > 0) {
            // adding infants
            url.append("&infants=");
            url.append(searchFlightItem.getInfants());
        }

        // adding nonstop
        url.append("&nonstop=");
        url.append(searchFlightItem.isNonstops());

        if (searchFlightItem.getMaxPrice() > 0) {
            // adding max_price
            url.append("&max_price=");
            url.append(searchFlightItem.getMaxPrice());
        }

        // adding travel_class
        if (searchFlightItem.getTravelClass() != null) {
            url.append("&travel_class=");
            url.append(searchFlightItem.getTravelClass());
        }

        // adding number_of_results
        url.append("&number_of_results=");
        url.append(50);

        return url.toString();
    }
}
