package com.hap.trip.model.flight;

import android.os.Parcel;
import android.os.Parcelable;

public class FlightData implements Parcelable {
    private final Fare fare;
    private final Itinerarie itinerarie;

    public FlightData(Fare fare, Itinerarie itinerarie) {
        this.fare = fare;
        this.itinerarie = itinerarie;
    }

    public Fare getFare() {
        return fare;
    }

    public Itinerarie getItinerarie() {
        return itinerarie;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.fare, flags);
        dest.writeParcelable(this.itinerarie, flags);
    }

    protected FlightData(Parcel in) {
        this.fare = in.readParcelable(Fare.class.getClassLoader());
        this.itinerarie = in.readParcelable(Itinerarie.class.getClassLoader());
    }

    public static final Parcelable.Creator<FlightData> CREATOR = new Parcelable.Creator<FlightData>() {
        @Override
        public FlightData createFromParcel(Parcel source) {
            return new FlightData(source);
        }

        @Override
        public FlightData[] newArray(int size) {
            return new FlightData[size];
        }
    };
}
