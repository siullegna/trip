package com.hap.trip.model.flight;

import android.os.Parcel;
import android.os.Parcelable;

public class Origin implements Parcelable {
    private String airport;

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.airport);
    }

    public Origin() {
    }

    protected Origin(Parcel in) {
        this.airport = in.readString();
    }

    public static final Parcelable.Creator<Origin> CREATOR = new Parcelable.Creator<Origin>() {
        @Override
        public Origin createFromParcel(Parcel source) {
            return new Origin(source);
        }

        @Override
        public Origin[] newArray(int size) {
            return new Origin[size];
        }
    };
}
