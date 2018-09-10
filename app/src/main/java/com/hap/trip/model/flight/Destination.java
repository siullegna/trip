package com.hap.trip.model.flight;

import android.os.Parcel;
import android.os.Parcelable;

public class Destination implements Parcelable {
    private String airport;
    private String terminal;

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.airport);
        dest.writeString(this.terminal);
    }

    public Destination() {
    }

    protected Destination(Parcel in) {
        this.airport = in.readString();
        this.terminal = in.readString();
    }

    public static final Parcelable.Creator<Destination> CREATOR = new Parcelable.Creator<Destination>() {
        @Override
        public Destination createFromParcel(Parcel source) {
            return new Destination(source);
        }

        @Override
        public Destination[] newArray(int size) {
            return new Destination[size];
        }
    };
}
