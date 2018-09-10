package com.hap.trip.model.flight;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class PricePerAdult implements Parcelable {
    private String tax;
    @SerializedName("total_fare")
    private String totalFare;

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(String totalFare) {
        this.totalFare = totalFare;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tax);
        dest.writeString(this.totalFare);
    }

    public PricePerAdult() {
    }

    protected PricePerAdult(Parcel in) {
        this.tax = in.readString();
        this.totalFare = in.readString();
    }

    public static final Parcelable.Creator<PricePerAdult> CREATOR = new Parcelable.Creator<PricePerAdult>() {
        @Override
        public PricePerAdult createFromParcel(Parcel source) {
            return new PricePerAdult(source);
        }

        @Override
        public PricePerAdult[] newArray(int size) {
            return new PricePerAdult[size];
        }
    };
}
