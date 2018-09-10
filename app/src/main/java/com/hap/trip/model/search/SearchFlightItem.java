package com.hap.trip.model.search;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Nonnull;

public class SearchFlightItem implements Parcelable {
    @Nonnull
    private final String origin;
    @Nonnull
    private final String originCode;
    @Nonnull
    private final String destination;
    @Nonnull
    private final String destinationCode;
    @Nonnull
    private final Long departureDate;
    private Long returnDate;
    private int adults = 1;
    private int children = 0;
    private int infants = 0;
    private boolean nonstops = false;
    private int maxPrice = -1;
    private String travelClass;

    public SearchFlightItem(@Nonnull String origin, @Nonnull String originCode, @Nonnull String destination, @Nonnull String destinationCode, @Nonnull Long departureDate) {
        this.origin = origin;
        this.originCode = originCode;
        this.destination = destination;
        this.destinationCode = destinationCode;
        this.departureDate = departureDate;
    }

    @Nonnull
    public String getOrigin() {
        return origin;
    }

    @Nonnull
    public String getOriginCode() {
        return originCode;
    }

    @Nonnull
    public String getDestination() {
        return destination;
    }

    @Nonnull
    public String getDestinationCode() {
        return destinationCode;
    }

    @Nonnull
    public Long getDepartureDate() {
        return departureDate;
    }

    public Long getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Long returnDate) {
        this.returnDate = returnDate;
    }

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public int getInfants() {
        return infants;
    }

    public void setInfants(int infants) {
        this.infants = infants;
    }

    public boolean isNonstops() {
        return nonstops;
    }

    public void setNonstops(boolean nonstops) {
        this.nonstops = nonstops;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
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
        dest.writeString(this.origin);
        dest.writeString(this.originCode);
        dest.writeString(this.destination);
        dest.writeString(this.destinationCode);
        dest.writeValue(this.departureDate);
        dest.writeValue(this.returnDate);
        dest.writeInt(this.adults);
        dest.writeInt(this.children);
        dest.writeInt(this.infants);
        dest.writeByte(this.nonstops ? (byte) 1 : (byte) 0);
        dest.writeInt(this.maxPrice);
        dest.writeString(this.travelClass);
    }

    protected SearchFlightItem(Parcel in) {
        this.origin = in.readString();
        this.originCode = in.readString();
        this.destination = in.readString();
        this.destinationCode = in.readString();
        this.departureDate = (Long) in.readValue(Long.class.getClassLoader());
        this.returnDate = (Long) in.readValue(Long.class.getClassLoader());
        this.adults = in.readInt();
        this.children = in.readInt();
        this.infants = in.readInt();
        this.nonstops = in.readByte() != 0;
        this.maxPrice = in.readInt();
        this.travelClass = in.readString();
    }

    public static final Creator<SearchFlightItem> CREATOR = new Creator<SearchFlightItem>() {
        @Override
        public SearchFlightItem createFromParcel(Parcel source) {
            return new SearchFlightItem(source);
        }

        @Override
        public SearchFlightItem[] newArray(int size) {
            return new SearchFlightItem[size];
        }
    };
}
