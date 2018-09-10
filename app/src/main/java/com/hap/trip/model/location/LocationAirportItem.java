package com.hap.trip.model.location;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.hap.trip.model.AirportItem;

public class LocationAirportItem implements Parcelable {
    @SerializedName("airport")
    private String airportCode;
    @SerializedName("airport_name")
    private String airportName;
    private String city;
    @SerializedName("city_name")
    private String cityName;
    private int distance;
    private Location location;
    private String timezone;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public static LocationAirportItem fromAirportItem(final AirportItem airportItem) {
        final LocationAirportItem locationAirportItem = new LocationAirportItem();

        locationAirportItem.airportCode = airportItem.getAirportCode();
        locationAirportItem.airportName = airportItem.getAirportName();
        locationAirportItem.city = airportItem.getCountry();
        locationAirportItem.cityName = airportItem.getCountry();

        return locationAirportItem;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.airportCode);
        dest.writeString(this.airportName);
        dest.writeString(this.city);
        dest.writeString(this.cityName);
        dest.writeInt(this.distance);
        dest.writeParcelable(this.location, flags);
        dest.writeString(this.timezone);
    }

    public LocationAirportItem() {
    }

    protected LocationAirportItem(Parcel in) {
        this.airportCode = in.readString();
        this.airportName = in.readString();
        this.city = in.readString();
        this.cityName = in.readString();
        this.distance = in.readInt();
        this.location = in.readParcelable(Location.class.getClassLoader());
        this.timezone = in.readString();
    }

    public static final Parcelable.Creator<LocationAirportItem> CREATOR = new Parcelable.Creator<LocationAirportItem>() {
        @Override
        public LocationAirportItem createFromParcel(Parcel source) {
            return new LocationAirportItem(source);
        }

        @Override
        public LocationAirportItem[] newArray(int size) {
            return new LocationAirportItem[size];
        }
    };
}
