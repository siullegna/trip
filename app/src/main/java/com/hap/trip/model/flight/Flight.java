package com.hap.trip.model.flight;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Flight implements Parcelable {
    private String aircraft;
    @SerializedName("arrives_at")
    private String arrivesAt;
    @SerializedName("booking_info")
    private BookingInfo bookingInfo;
    @SerializedName("departs_at")
    private String departsAt;
    private Destination destination;
    @SerializedName("flight_number")
    private String flightNumber;
    @SerializedName("marketing_airline")
    private String marketingAirline;
    @SerializedName("operating_airline")
    private String operatingAirline;
    private Origin origin;

    public String getAircraft() {
        return aircraft;
    }

    public void setAircraft(String aircraft) {
        this.aircraft = aircraft;
    }

    public String getArrivesAt() {
        return arrivesAt;
    }

    public void setArrivesAt(String arrivesAt) {
        this.arrivesAt = arrivesAt;
    }

    public BookingInfo getBookingInfo() {
        return bookingInfo;
    }

    public void setBookingInfo(BookingInfo bookingInfo) {
        this.bookingInfo = bookingInfo;
    }

    public String getDepartsAt() {
        return departsAt;
    }

    public void setDepartsAt(String departsAt) {
        this.departsAt = departsAt;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getMarketingAirline() {
        return marketingAirline;
    }

    public void setMarketingAirline(String marketingAirline) {
        this.marketingAirline = marketingAirline;
    }

    public String getOperatingAirline() {
        return operatingAirline;
    }

    public void setOperatingAirline(String operatingAirline) {
        this.operatingAirline = operatingAirline;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.aircraft);
        dest.writeString(this.arrivesAt);
        dest.writeParcelable(this.bookingInfo, flags);
        dest.writeString(this.departsAt);
        dest.writeParcelable(this.destination, flags);
        dest.writeString(this.flightNumber);
        dest.writeString(this.marketingAirline);
        dest.writeString(this.operatingAirline);
        dest.writeParcelable(this.origin, flags);
    }

    public Flight() {
    }

    protected Flight(Parcel in) {
        this.aircraft = in.readString();
        this.arrivesAt = in.readString();
        this.bookingInfo = in.readParcelable(BookingInfo.class.getClassLoader());
        this.departsAt = in.readString();
        this.destination = in.readParcelable(Destination.class.getClassLoader());
        this.flightNumber = in.readString();
        this.marketingAirline = in.readString();
        this.operatingAirline = in.readString();
        this.origin = in.readParcelable(Origin.class.getClassLoader());
    }

    public static final Parcelable.Creator<Flight> CREATOR = new Parcelable.Creator<Flight>() {
        @Override
        public Flight createFromParcel(Parcel source) {
            return new Flight(source);
        }

        @Override
        public Flight[] newArray(int size) {
            return new Flight[size];
        }
    };
}
