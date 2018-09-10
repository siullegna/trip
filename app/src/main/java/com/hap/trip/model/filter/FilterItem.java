package com.hap.trip.model.filter;

import android.os.Parcel;
import android.os.Parcelable;

public class FilterItem implements Parcelable {
    private SortType sortType = SortType.DEFAULT;
    private int durationHours = 24; // this means all
    private boolean isNonStop = false;
    private boolean isOneStop = false;
    private boolean isTwoPlusStops = false;

    public SortType getSortType() {
        return sortType;
    }

    public void setSortType(SortType sortType) {
        this.sortType = sortType;
    }

    public int getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(int durationHours) {
        this.durationHours = durationHours;
    }

    public boolean isNonStop() {
        return isNonStop;
    }

    public void setNonStop(boolean nonStop) {
        isNonStop = nonStop;
    }

    public boolean isOneStop() {
        return isOneStop;
    }

    public void setOneStop(boolean oneStop) {
        isOneStop = oneStop;
    }

    public boolean isTwoPlusStops() {
        return isTwoPlusStops;
    }

    public void setTwoPlusStops(boolean twoPlusStops) {
        isTwoPlusStops = twoPlusStops;
    }

    public enum SortType {
        DEFAULT(0),
        PRICE(1),
        AIRLINE(2);

        private final int sortType;

        SortType(int sortType) {
            this.sortType = sortType;
        }

        public int getSortType() {
            return sortType;
        }

        public static SortType fromType(final int type) {
            SortType newSortType = DEFAULT;

            for (final SortType current : values()) {
                if (current.sortType == type) {
                    newSortType = current;
                    break;
                }
            }

            return newSortType;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.sortType == null ? -1 : this.sortType.ordinal());
        dest.writeInt(this.durationHours);
        dest.writeByte(this.isNonStop ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isOneStop ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isTwoPlusStops ? (byte) 1 : (byte) 0);
    }

    public FilterItem() {
    }

    protected FilterItem(Parcel in) {
        int tmpSortType = in.readInt();
        this.sortType = tmpSortType == -1 ? null : SortType.values()[tmpSortType];
        this.durationHours = in.readInt();
        this.isNonStop = in.readByte() != 0;
        this.isOneStop = in.readByte() != 0;
        this.isTwoPlusStops = in.readByte() != 0;
    }

    public static final Parcelable.Creator<FilterItem> CREATOR = new Parcelable.Creator<FilterItem>() {
        @Override
        public FilterItem createFromParcel(Parcel source) {
            return new FilterItem(source);
        }

        @Override
        public FilterItem[] newArray(int size) {
            return new FilterItem[size];
        }
    };
}
