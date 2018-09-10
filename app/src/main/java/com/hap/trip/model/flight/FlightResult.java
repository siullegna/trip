package com.hap.trip.model.flight;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class FlightResult implements Parcelable {
    private Fare fare;
    private ArrayList<Itinerarie> itineraries;

    public Fare getFare() {
        return fare;
    }

    public void setFare(Fare fare) {
        this.fare = fare;
    }

    public ArrayList<Itinerarie> getItineraries() {
        return itineraries;
    }

    public void setItineraries(ArrayList<Itinerarie> itineraries) {
        this.itineraries = itineraries;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.fare, flags);
        dest.writeTypedList(this.itineraries);
    }

    public FlightResult() {
    }

    protected FlightResult(Parcel in) {
        this.fare = in.readParcelable(Fare.class.getClassLoader());
        this.itineraries = in.createTypedArrayList(Itinerarie.CREATOR);
    }

    public static final Parcelable.Creator<FlightResult> CREATOR = new Parcelable.Creator<FlightResult>() {
        @Override
        public FlightResult createFromParcel(Parcel source) {
            return new FlightResult(source);
        }

        @Override
        public FlightResult[] newArray(int size) {
            return new FlightResult[size];
        }
    };
}
