package com.hap.trip.model.flight;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Restrictions implements Parcelable {
    @SerializedName("change_penalties")
    private boolean isChangePenalties;
    private boolean refundable;

    public boolean isChangePenalties() {
        return isChangePenalties;
    }

    public void setChangePenalties(boolean changePenalties) {
        isChangePenalties = changePenalties;
    }

    public boolean isRefundable() {
        return refundable;
    }

    public void setRefundable(boolean refundable) {
        this.refundable = refundable;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.isChangePenalties ? (byte) 1 : (byte) 0);
        dest.writeByte(this.refundable ? (byte) 1 : (byte) 0);
    }

    public Restrictions() {
    }

    protected Restrictions(Parcel in) {
        this.isChangePenalties = in.readByte() != 0;
        this.refundable = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Restrictions> CREATOR = new Parcelable.Creator<Restrictions>() {
        @Override
        public Restrictions createFromParcel(Parcel source) {
            return new Restrictions(source);
        }

        @Override
        public Restrictions[] newArray(int size) {
            return new Restrictions[size];
        }
    };
}
