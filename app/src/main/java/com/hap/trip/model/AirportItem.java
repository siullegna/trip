package com.hap.trip.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by luis on 6/16/18.
 */

public class AirportItem implements Parcelable {
    @SerializedName("value")
    private String airportCode;
    @SerializedName("label")
    private String airportName;
    private String country;
    private boolean isCurrentLocation = false;

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isCurrentLocation() {
        return isCurrentLocation;
    }

    public void setCurrentLocation(boolean isCurrentLocation) {
        this.isCurrentLocation = isCurrentLocation;
    }

    public AirportItem() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.airportCode);
        dest.writeString(this.airportName);
        dest.writeString(this.country);
        dest.writeByte(this.isCurrentLocation ? (byte) 1 : (byte) 0);
    }

    protected AirportItem(Parcel in) {
        this.airportCode = in.readString();
        this.airportName = in.readString();
        this.country = in.readString();
        this.isCurrentLocation = in.readByte() != 0;
    }

    public static final Creator<AirportItem> CREATOR = new Creator<AirportItem>() {
        @Override
        public AirportItem createFromParcel(Parcel source) {
            return new AirportItem(source);
        }

        @Override
        public AirportItem[] newArray(int size) {
            return new AirportItem[size];
        }
    };
}
