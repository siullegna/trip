package com.hap.trip.model.flight;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Fare implements Parcelable {
    @SerializedName("price_per_adult")
    private PricePerAdult pricePerAdult;
    private Restrictions restrictions;
    @SerializedName("total_price")
    private String totalPrice;

    public PricePerAdult getPricePerAdult() {
        return pricePerAdult;
    }

    public void setPricePerAdult(PricePerAdult pricePerAdult) {
        this.pricePerAdult = pricePerAdult;
    }

    public Restrictions getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(Restrictions restrictions) {
        this.restrictions = restrictions;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.pricePerAdult, flags);
        dest.writeParcelable(this.restrictions, flags);
        dest.writeString(this.totalPrice);
    }

    public Fare() {
    }

    protected Fare(Parcel in) {
        this.pricePerAdult = in.readParcelable(PricePerAdult.class.getClassLoader());
        this.restrictions = in.readParcelable(Restrictions.class.getClassLoader());
        this.totalPrice = in.readString();
    }

    public static final Parcelable.Creator<Fare> CREATOR = new Parcelable.Creator<Fare>() {
        @Override
        public Fare createFromParcel(Parcel source) {
            return new Fare(source);
        }

        @Override
        public Fare[] newArray(int size) {
            return new Fare[size];
        }
    };
}
