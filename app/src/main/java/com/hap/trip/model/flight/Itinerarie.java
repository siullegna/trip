package com.hap.trip.model.flight;

import android.os.Parcel;
import android.os.Parcelable;

public class Itinerarie implements Parcelable {
    private Inbound inbound;
    private Outbound outbound;

    public Inbound getInbound() {
        return inbound;
    }

    public void setInbound(Inbound inbound) {
        this.inbound = inbound;
    }

    public Outbound getOutbound() {
        return outbound;
    }

    public void setOutbound(Outbound outbound) {
        this.outbound = outbound;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.inbound, flags);
        dest.writeParcelable(this.outbound, flags);
    }

    public Itinerarie() {
    }

    protected Itinerarie(Parcel in) {
        this.inbound = in.readParcelable(Inbound.class.getClassLoader());
        this.outbound = in.readParcelable(Outbound.class.getClassLoader());
    }

    public static final Creator<Itinerarie> CREATOR = new Creator<Itinerarie>() {
        @Override
        public Itinerarie createFromParcel(Parcel source) {
            return new Itinerarie(source);
        }

        @Override
        public Itinerarie[] newArray(int size) {
            return new Itinerarie[size];
        }
    };
}
