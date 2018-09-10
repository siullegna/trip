package com.hap.trip.model.flight;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class FlightItem implements Parcelable {
    private String currency;
    private ArrayList<FlightResult> results;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public ArrayList<FlightResult> getResults() {
        return results;
    }

    public void setResults(ArrayList<FlightResult> results) {
        this.results = results;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.currency);
        dest.writeTypedList(this.results);
    }

    public FlightItem() {
    }

    protected FlightItem(Parcel in) {
        this.currency = in.readString();
        this.results = in.createTypedArrayList(FlightResult.CREATOR);
    }

    public static final Parcelable.Creator<FlightItem> CREATOR = new Parcelable.Creator<FlightItem>() {
        @Override
        public FlightItem createFromParcel(Parcel source) {
            return new FlightItem(source);
        }

        @Override
        public FlightItem[] newArray(int size) {
            return new FlightItem[size];
        }
    };
}
