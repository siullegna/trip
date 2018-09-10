package com.hap.trip.model.flight;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Inbound implements Parcelable {
    private String duration;
    private ArrayList<Flight> flights;

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }

    public void setFlights(ArrayList<Flight> flights) {
        this.flights = flights;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.duration);
        dest.writeTypedList(this.flights);
    }

    public Inbound() {
    }

    protected Inbound(Parcel in) {
        this.duration = in.readString();
        this.flights = in.createTypedArrayList(Flight.CREATOR);
    }

    public static final Creator<Inbound> CREATOR = new Creator<Inbound>() {
        @Override
        public Inbound createFromParcel(Parcel source) {
            return new Inbound(source);
        }

        @Override
        public Inbound[] newArray(int size) {
            return new Inbound[size];
        }
    };
}
