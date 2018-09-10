package com.hap.trip.util;

import android.content.res.Resources;
import android.util.Log;

import com.hap.trip.R;
import com.hap.trip.TripApplication;
import com.hap.trip.model.filter.FilterItem;
import com.hap.trip.model.flight.Flight;
import com.hap.trip.model.flight.FlightData;
import com.hap.trip.model.flight.FlightResult;
import com.hap.trip.model.flight.Inbound;
import com.hap.trip.model.flight.Itinerarie;
import com.hap.trip.model.flight.Outbound;
import com.hap.trip.model.search.SearchFlightItem;
import com.hap.trip.persistence.room.entity.FlightDetail;
import com.hap.trip.widget.FlightCardView;

import java.util.ArrayList;
import java.util.Collections;

import javax.annotation.Nonnull;

public class FlightUtil {
    private static final String TAG = FlightUtil.class.getName();

    public static ArrayList<FlightData> getFlightData(final ArrayList<FlightResult> results) {
        final ArrayList<FlightData> flightDataList = new ArrayList<>();

        for (final FlightResult result : results) {
            for (final Itinerarie itinerarie : result.getItineraries()) {
                flightDataList.add(new FlightData(result.getFare(), itinerarie));
            }
        }

        return flightDataList;
    }

    public static ArrayList<FlightData> getFlightData(final ArrayList<FlightResult> results, final FilterItem filterItem) {
        final ArrayList<FlightData> flightDataList = new ArrayList<>();

        for (final FlightResult result : results) {
            for (final Itinerarie itinerarie : result.getItineraries()) {
                flightDataList.add(new FlightData(result.getFare(), itinerarie));
            }
        }

        final ArrayList<FlightData> flightsToRemove = new ArrayList<>();
        for (final FlightData flightData : flightDataList) {
            final String durationParts[] = flightData.getItinerarie().getOutbound().getDuration().split(":");
            try {
                if (Integer.parseInt(durationParts[0]) > filterItem.getDurationHours() || (Integer.parseInt(durationParts[0]) == filterItem.getDurationHours() && Integer.parseInt(durationParts[1]) > 0)) {
                    flightsToRemove.add(flightData);
                } else if (filterItem.isNonStop()) {
                    if (flightData.getItinerarie().getOutbound().getFlights().size() > 1) {
                        flightsToRemove.add(flightData);
                    }
                } else if (filterItem.isOneStop()) {
                    if (flightData.getItinerarie().getOutbound().getFlights().size() != 2) {
                        flightsToRemove.add(flightData);
                    }
                } else if (filterItem.isTwoPlusStops()) {
                    if (flightData.getItinerarie().getOutbound().getFlights().size() < 3) {
                        flightsToRemove.add(flightData);
                    }
                }
            } catch (NumberFormatException e) {
                Log.d(TAG, e.getMessage());
            }
        }

        flightDataList.removeAll(flightsToRemove);

        // sort
        switch (filterItem.getSortType()) {
            case PRICE:
                Collections.sort(flightDataList, new SortFlightByPrice());
                break;
            case AIRLINE:
                Collections.sort(flightDataList, new SortFlightByAirline());
                break;
        }

        return flightDataList;
    }

    private static String getFlightStops(final ArrayList<Flight> flights) {
        if (flights.isEmpty()) {
            return null;
        }

        String stops;
        final Resources resources = TripApplication.getInstance().getResources();

        if (flights.size() > 1) {
            stops = resources.getQuantityString(R.plurals.flight_result_stops, flights.size(), flights.size() - 1);
        } else {
            stops = resources.getString(R.string.flight_result_nonstop);
        }

        return stops;
    }

    private static String getFlightTime(final ArrayList<Flight> flights) {
        if (flights.isEmpty()) {
            return null;
        }

        String depart;
        String arrive;

        try {
            depart = DateUtil.getFormattedTime(flights.get(0).getDepartsAt());
            if (flights.size() > 1) {
                arrive = DateUtil.getFormattedTime(flights.get(flights.size() - 1).getArrivesAt());
            } else {
                arrive = DateUtil.getFormattedTime(flights.get(0).getArrivesAt());
            }
        } catch (NullPointerException e) {
            return null;
        }

        return TripApplication.getInstance().getResources().getString(R.string.flight_result_time_format_1, depart, arrive);
    }

    private static String getAirline(final ArrayList<Flight> flights) {
        if (flights.isEmpty()) {
            return null;
        }

        final StringBuilder sb = new StringBuilder();
        try {
            sb.append(flights.get(0).getAircraft());

            final Resources resources = TripApplication.getInstance().getResources();
            for (int i = 1; i < flights.size(); i++) {
                sb.append(resources.getString(R.string.flight_result_aircraft_format, flights.get(i).getAircraft()));
            }
        } catch (NullPointerException e) {
            return null;
        }

        return sb.toString();
    }

    private static String getFlightTypeEconomy(final ArrayList<Flight> flights) {
        if (flights.isEmpty()) {
            return null;
        }

        final StringBuilder sb = new StringBuilder();
        try {
            sb.append(flights.get(0).getBookingInfo().getTravelClass());

            final Resources resources = TripApplication.getInstance().getResources();
            for (int i = 1; i < flights.size(); i++) {
                sb.append(resources.getString(R.string.flight_result_type_format, flights.get(i).getBookingInfo().getTravelClass()));
            }
        } catch (NullPointerException e) {
            return null;
        }

        return sb.toString();
    }

    public static String getFormattedPassenger(final Resources resources, final int adultCount, final int childrenCount, final int infantCount) {
        return resources.getString(R.string.flight_passenger_format, adultCount, childrenCount, infantCount);
    }

    public static FlightCardView.Builder getFlightResultCardBuilder(@Nonnull final FlightData flightData) {
        final FlightCardView.Builder cardBuilder = new FlightCardView.Builder(TripApplication.getInstance());

        final String flightDuration = TripApplication.getInstance().getResources().getString(R.string.flight_result_time_format_2, flightData.getItinerarie().getOutbound().getDuration(), getFlightStops(flightData.getItinerarie().getOutbound().getFlights()));

        cardBuilder
                .setPrice(flightData.getFare().getTotalPrice())
                .setFlightTime(getFlightTime(flightData.getItinerarie().getOutbound().getFlights()))
                .setAirline(getAirline(flightData.getItinerarie().getOutbound().getFlights()))
                .setDuration(flightDuration)
                .setClassType(getFlightTypeEconomy(flightData.getItinerarie().getOutbound().getFlights()))
                .build();

        return cardBuilder;
    }

    private static ArrayList<FlightCardView.Builder> getFlightListBuilder(final ArrayList<FlightDetail> flights) {
        final ArrayList<FlightCardView.Builder> cardBuilderList = new ArrayList<>();

        for (final FlightDetail flight : flights) {
            final FlightCardView.Builder cardBuilder = new FlightCardView.Builder(TripApplication.getInstance());

            cardBuilder
                    .setFlightTime(flight.getTime())
                    .setAirline(flight.getAircraft())
                    .setClassType(flight.getClassType())
                    .setDuration(flight.getDuration())
                    .setFlightNumber(TripApplication.getInstance().getResources().getString(R.string.flight_detail_number, flight.getFlightNumber()))
                    .removeDivider()
                    .build();

            cardBuilderList.add(cardBuilder);
        }

        return cardBuilderList;
    }

    public static ArrayList<FlightCardView.Builder> getOutboundBuilder(final ArrayList<FlightDetail> flights) {
        if (flights == null) {
            return new ArrayList<>();
        }

        final ArrayList<FlightCardView.Builder> departingBuilder = getFlightListBuilder(flights);

        if (!departingBuilder.isEmpty()) {
            departingBuilder.get(departingBuilder.size() - 1)
                    .addDivider();
        }

        return departingBuilder;
    }

    public static ArrayList<FlightCardView.Builder> getInboundBuilder(final ArrayList<FlightDetail> flights) {
        final ArrayList<FlightCardView.Builder> returningBuilder = getFlightListBuilder(flights);

        if (!returningBuilder.isEmpty()) {
            returningBuilder.get(returningBuilder.size() - 1)
                    .addDivider();
        }

        return returningBuilder;
    }

    private static ArrayList<FlightDetail> getDetailList(final ArrayList<Flight> flights, final FlightDetail.DetailType detailType) {
        final ArrayList<FlightDetail> flightDetails = new ArrayList<>();

        int sequence = 1;
        for (final Flight flight : flights) {
            final String depart = DateUtil.getFormattedTime(flight.getDepartsAt());
            final String arrive = DateUtil.getFormattedTime(flight.getArrivesAt());
            final String flightTime = TripApplication.getInstance().getResources().getString(R.string.flight_result_time_format_1, depart, arrive);

            final FlightDetail flightDetail = new FlightDetail(flightTime,
                    flight.getAircraft(),
                    DateUtil.getTimeDifference(flight.getDepartsAt(), flight.getArrivesAt()),
                    flight.getBookingInfo().getTravelClass(),
                    flight.getFlightNumber(),
                    sequence++,
                    detailType);

            flightDetails.add(flightDetail);
        }

        return flightDetails;
    }

    public static ArrayList<FlightDetail> getOutboundDetailList(final Outbound outbound) {
        if (outbound == null) {
            return new ArrayList<>();
        }

        final ArrayList<Flight> flights = outbound.getFlights();
        return getDetailList(flights, FlightDetail.DetailType.OUTBOUND);
    }

    public static ArrayList<FlightDetail> getInboundDetailList(final Inbound inbound) {
        if (inbound == null) {
            return new ArrayList<>();
        }

        final ArrayList<Flight> flights = inbound.getFlights();
        return getDetailList(flights, FlightDetail.DetailType.INBOUND);
    }

    public static int getTotalOfTravelers(final SearchFlightItem searchFlightItem) {
        return searchFlightItem.getAdults() + searchFlightItem.getChildren() + searchFlightItem.getInfants();
    }

    public static String getFlightRoute(final ArrayList<Flight> flights) {
        final StringBuilder sb = new StringBuilder();

        if (flights.isEmpty()) {
            return sb.toString();
        }

        final Resources resources = TripApplication.getInstance().getResources();
        sb.append(flights.get(0).getOrigin().getAirport());
        sb.append(resources.getString(R.string.flight_route_format, flights.get(0).getDestination().getAirport()));

        for (int i = 1; i < flights.size(); i++) {
            sb.append(resources.getString(R.string.flight_route_format, flights.get(i).getDestination().getAirport()));
        }

        return sb.toString();
    }
}
