package com.hap.trip.model.flight;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class BookingInfo implements Parcelable {
    @SerializedName("booking_code")
    private String bookingCode;
    @SerializedName("seats_remaining")
    private int seatsRemaining;
    @SerializedName("travel_class")
    private String travelClass;

    public String getBookingCode() {
        return bookingCode;
    }

    public void setBookingCode(String bookingCode) {
        this.bookingCode = bookingCode;
    }

    public int getSeatsRemaining() {
        return seatsRemaining;
    }

    public void setSeatsRemaining(int seatsRemaining) {
        this.seatsRemaining = seatsRemaining;
    }

    public String getTravelClass() {
        return travelClass;
    }

    public void setTravelClass(String travelClass) {
        this.travelClass = travelClass;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.bookingCode);
        dest.writeInt(this.seatsRemaining);
        dest.writeString(this.travelClass);
    }

    public BookingInfo() {
    }

    protected BookingInfo(Parcel in) {
        this.bookingCode = in.readString();
        this.seatsRemaining = in.readInt();
        this.travelClass = in.readString();
    }

    public static final Parcelable.Creator<BookingInfo> CREATOR = new Parcelable.Creator<BookingInfo>() {
        @Override
        public BookingInfo createFromParcel(Parcel source) {
            return new BookingInfo(source);
        }

        @Override
        public BookingInfo[] newArray(int size) {
            return new BookingInfo[size];
        }
    };
}
