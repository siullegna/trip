package com.hap.trip.persistence.room.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.hap.trip.model.flight.FlightData;
import com.hap.trip.model.flight.Inbound;
import com.hap.trip.model.flight.Outbound;
import com.hap.trip.model.search.SearchFlightItem;
import com.hap.trip.persistence.FlightContract;
import com.hap.trip.persistence.room.converter.DateConverter;
import com.hap.trip.persistence.room.converter.FlightConverter;
import com.hap.trip.util.DateUtil;
import com.hap.trip.util.FlightUtil;

import java.util.ArrayList;
import java.util.Date;

@Entity(tableName = FlightContract.FlightEntity.TBL_FLIGHT, indices = {@Index(FlightContract.FlightEntity._ID)})
public class FlightEntity implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = FlightContract.FlightEntity._ID)
    private int id;

    @ColumnInfo(name = FlightContract.FlightEntity.COLUMN_OUTBOUND)
    private String outbound;
    @ColumnInfo(name = FlightContract.FlightEntity.COLUMN_OUTBOUND_ROUTE)
    private String outboundRoute;
    @ColumnInfo(name = FlightContract.FlightEntity.COLUMN_OUTBOUND_DURATION)
    private String outboundDuration;
    @ColumnInfo(name = FlightContract.FlightEntity.COLUMN_OUTBOUND_DATE)
    private String outboundDate;
    @ColumnInfo(name = FlightContract.FlightEntity.COLUMN_OUTBOUND_FLIGHTS)
    private String outboundFlights;

    @ColumnInfo(name = FlightContract.FlightEntity.COLUMN_INBOUND)
    private String inbound;
    @ColumnInfo(name = FlightContract.FlightEntity.COLUMN_INBOUND_ROUTE)
    private String inboundRoute;
    @ColumnInfo(name = FlightContract.FlightEntity.COLUMN_INBOUND_DURATION)
    private String inboundDuration;
    @ColumnInfo(name = FlightContract.FlightEntity.COLUMN_INBOUND_DATE)
    private String inboundDate;
    @ColumnInfo(name = FlightContract.FlightEntity.COLUMN_INBOUND_FLIGHTS)
    private String inboundFlights;

    @ColumnInfo(name = FlightContract.FlightEntity.COLUMN_PASSENGERS)
    private int passengers;
    @ColumnInfo(name = FlightContract.FlightEntity.COLUMN_PRICE)
    private String price;
    @ColumnInfo(name = FlightContract.FlightEntity.COLUMN_CREATION_DATE)
    private Date creationDate = new Date(System.currentTimeMillis());

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOutbound() {
        return outbound;
    }

    public void setOutbound(String outbound) {
        this.outbound = outbound;
    }

    public String getOutboundRoute() {
        return outboundRoute;
    }

    public void setOutboundRoute(String outboundRoute) {
        this.outboundRoute = outboundRoute;
    }

    public String getOutboundDuration() {
        return outboundDuration;
    }

    public void setOutboundDuration(String outboundDuration) {
        this.outboundDuration = outboundDuration;
    }

    public String getOutboundDate() {
        return outboundDate;
    }

    public void setOutboundDate(String outboundDate) {
        this.outboundDate = outboundDate;
    }

    public ArrayList<FlightDetail> getOutboundFlightsList() {
        return FlightConverter.fromString(outboundFlights);
    }

    public String getOutboundFlights() {
        return outboundFlights;
    }

    public void setOutboundFlights(String outboundFlights) {
        this.outboundFlights = outboundFlights;
    }

    public String getInbound() {
        return inbound;
    }

    public void setInbound(String inbound) {
        this.inbound = inbound;
    }

    public String getInboundRoute() {
        return inboundRoute;
    }

    public void setInboundRoute(String inboundRoute) {
        this.inboundRoute = inboundRoute;
    }

    public String getInboundDuration() {
        return inboundDuration;
    }

    public void setInboundDuration(String inboundDuration) {
        this.inboundDuration = inboundDuration;
    }

    public String getInboundDate() {
        return inboundDate;
    }

    public void setInboundDate(String inboundDate) {
        this.inboundDate = inboundDate;
    }

    public ArrayList<FlightDetail> getInboundFlightsList() {
        return FlightConverter.fromString(inboundFlights);
    }

    public String getInboundFlights() {
        return inboundFlights;
    }

    public void setInboundFlights(String inboundFlights) {
        this.inboundFlights = inboundFlights;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public static FlightEntity fromDetails(final FlightData flightData, final SearchFlightItem searchFlightItem) {
        final FlightEntity flightEntity = new FlightEntity();

        // outbound
        final Outbound outbound = flightData.getItinerarie().getOutbound();
        flightEntity.setOutbound(searchFlightItem.getOrigin());
        flightEntity.setOutboundRoute(FlightUtil.getFlightRoute(outbound.getFlights()));
        flightEntity.setOutboundDuration(outbound.getDuration());
        flightEntity.setOutboundDate(DateUtil.getSelectedDateFormatted(searchFlightItem.getDepartureDate()));
        final ArrayList<FlightDetail> outboundFlights = FlightUtil.getOutboundDetailList(outbound);
        flightEntity.setOutboundFlights(FlightConverter.fromArrayList(outboundFlights));

        // inbound
        final Inbound inbound = flightData.getItinerarie().getInbound();
        if (inbound != null) {
            flightEntity.setInbound(searchFlightItem.getDestination());
            flightEntity.setInboundRoute(FlightUtil.getFlightRoute(inbound.getFlights()));
            flightEntity.setInboundDuration(inbound.getDuration());
            flightEntity.setInboundDate(DateUtil.getSelectedDateFormatted(searchFlightItem.getReturnDate()));
            final ArrayList<FlightDetail> inboundFlights = FlightUtil.getInboundDetailList(inbound);
            flightEntity.setInboundFlights(FlightConverter.fromArrayList(inboundFlights));
        }

        flightEntity.setPassengers(FlightUtil.getTotalOfTravelers(searchFlightItem));
        flightEntity.setPrice(flightData.getFare().getTotalPrice());

        return flightEntity;
    }

    public static FlightEntity fromContentValues(final ContentValues values) {
        final FlightEntity flightEntity = new FlightEntity();

        if (values.containsKey(FlightContract.FlightEntity._ID)) {
            flightEntity.setId(values.getAsInteger(FlightContract.FlightEntity._ID));
        }

        if (values.containsKey(FlightContract.FlightEntity.COLUMN_OUTBOUND)) {
            flightEntity.setOutbound(values.getAsString(FlightContract.FlightEntity.COLUMN_OUTBOUND));
        }

        if (values.containsKey(FlightContract.FlightEntity.COLUMN_OUTBOUND_ROUTE)) {
            flightEntity.setOutboundRoute(values.getAsString(FlightContract.FlightEntity.COLUMN_OUTBOUND_ROUTE));
        }

        if (values.containsKey(FlightContract.FlightEntity.COLUMN_OUTBOUND_DURATION)) {
            flightEntity.setOutboundDuration(values.getAsString(FlightContract.FlightEntity.COLUMN_OUTBOUND_DURATION));
        }

        if (values.containsKey(FlightContract.FlightEntity.COLUMN_OUTBOUND_DATE)) {
            flightEntity.setOutboundDate(values.getAsString(FlightContract.FlightEntity.COLUMN_OUTBOUND_DATE));
        }

        if (values.containsKey(FlightContract.FlightEntity.COLUMN_OUTBOUND_FLIGHTS)) {
            flightEntity.setOutboundFlights(values.getAsString(FlightContract.FlightEntity.COLUMN_OUTBOUND_FLIGHTS));
        }

        if (values.containsKey(FlightContract.FlightEntity.COLUMN_INBOUND)) {
            flightEntity.setInbound(values.getAsString(FlightContract.FlightEntity.COLUMN_INBOUND));
        }

        if (values.containsKey(FlightContract.FlightEntity.COLUMN_INBOUND_ROUTE)) {
            flightEntity.setInboundRoute(values.getAsString(FlightContract.FlightEntity.COLUMN_INBOUND_ROUTE));
        }

        if (values.containsKey(FlightContract.FlightEntity.COLUMN_INBOUND_DURATION)) {
            flightEntity.setInboundDuration(values.getAsString(FlightContract.FlightEntity.COLUMN_INBOUND_DURATION));
        }

        if (values.containsKey(FlightContract.FlightEntity.COLUMN_INBOUND_DATE)) {
            flightEntity.setInboundDate(values.getAsString(FlightContract.FlightEntity.COLUMN_INBOUND_DATE));
        }

        if (values.containsKey(FlightContract.FlightEntity.COLUMN_INBOUND_FLIGHTS)) {
            flightEntity.setInboundFlights(values.getAsString(FlightContract.FlightEntity.COLUMN_INBOUND_FLIGHTS));
        }

        if (values.containsKey(FlightContract.FlightEntity.COLUMN_PASSENGERS)) {
            flightEntity.setPassengers(values.getAsInteger(FlightContract.FlightEntity.COLUMN_PASSENGERS));
        }

        if (values.containsKey(FlightContract.FlightEntity.COLUMN_PRICE)) {
            flightEntity.setPrice(values.getAsString(FlightContract.FlightEntity.COLUMN_PRICE));
        }

        if (values.containsKey(FlightContract.FlightEntity.COLUMN_CREATION_DATE)) {
            flightEntity.setCreationDate(DateConverter.fromLong(values.getAsLong(FlightContract.FlightEntity.COLUMN_CREATION_DATE)));
        }

        return flightEntity;
    }

    public static FlightEntity fromCursor(final Cursor cursor) {
        final FlightEntity flightEntity = new FlightEntity();

        final int idColumn = cursor.getColumnIndex(FlightContract.FlightEntity._ID);
        final int outboundColumn = cursor.getColumnIndex(FlightContract.FlightEntity.COLUMN_OUTBOUND);
        final int outboundRouteColumn = cursor.getColumnIndex(FlightContract.FlightEntity.COLUMN_OUTBOUND_ROUTE);
        final int outboundDurationColumn = cursor.getColumnIndex(FlightContract.FlightEntity.COLUMN_OUTBOUND_DURATION);
        final int outboundDateColumn = cursor.getColumnIndex(FlightContract.FlightEntity.COLUMN_OUTBOUND_DATE);
        final int outboundFlightsColumn = cursor.getColumnIndex(FlightContract.FlightEntity.COLUMN_OUTBOUND_FLIGHTS);
        final int inboundColumn = cursor.getColumnIndex(FlightContract.FlightEntity.COLUMN_INBOUND);
        final int inboundRouteColumn = cursor.getColumnIndex(FlightContract.FlightEntity.COLUMN_INBOUND_ROUTE);
        final int inboundDurationColumn = cursor.getColumnIndex(FlightContract.FlightEntity.COLUMN_INBOUND_DURATION);
        final int inboundDateColumn = cursor.getColumnIndex(FlightContract.FlightEntity.COLUMN_INBOUND_DATE);
        final int inboundFlightsColumn = cursor.getColumnIndex(FlightContract.FlightEntity.COLUMN_INBOUND_FLIGHTS);
        final int passengerColumn = cursor.getColumnIndex(FlightContract.FlightEntity.COLUMN_PASSENGERS);
        final int priceColumn = cursor.getColumnIndex(FlightContract.FlightEntity.COLUMN_PRICE);
        final int creationDateColumn = cursor.getColumnIndex(FlightContract.FlightEntity.COLUMN_CREATION_DATE);

        flightEntity.setId(cursor.getInt(idColumn));
        flightEntity.setOutbound(cursor.getString(outboundColumn));
        flightEntity.setOutboundRoute(cursor.getString(outboundRouteColumn));
        flightEntity.setOutboundDuration(cursor.getString(outboundDurationColumn));
        flightEntity.setOutboundDate(cursor.getString(outboundDateColumn));
        flightEntity.setOutboundFlights(cursor.getString(outboundFlightsColumn));
        flightEntity.setInbound(cursor.getString(inboundColumn));
        flightEntity.setInboundRoute(cursor.getString(inboundRouteColumn));
        flightEntity.setInboundDuration(cursor.getString(inboundDurationColumn));
        flightEntity.setInboundDate(cursor.getString(inboundDateColumn));
        flightEntity.setInboundFlights(cursor.getString(inboundFlightsColumn));
        flightEntity.setPassengers(cursor.getInt(passengerColumn));
        flightEntity.setPrice(cursor.getString(priceColumn));
        flightEntity.setCreationDate(DateConverter.fromLong(cursor.getLong(creationDateColumn)));

        return flightEntity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.outbound);
        dest.writeString(this.outboundRoute);
        dest.writeString(this.outboundDuration);
        dest.writeString(this.outboundDate);
        dest.writeString(this.outboundFlights);
        dest.writeString(this.inbound);
        dest.writeString(this.inboundRoute);
        dest.writeString(this.inboundDuration);
        dest.writeString(this.inboundDate);
        dest.writeString(this.inboundFlights);
        dest.writeInt(this.passengers);
        dest.writeString(this.price);
        dest.writeLong(this.creationDate != null ? this.creationDate.getTime() : -1);
    }

    public FlightEntity() {
    }

    protected FlightEntity(Parcel in) {
        this.id = in.readInt();
        this.outbound = in.readString();
        this.outboundRoute = in.readString();
        this.outboundDuration = in.readString();
        this.outboundDate = in.readString();
        this.outboundFlights = in.readString();
        this.inbound = in.readString();
        this.inboundRoute = in.readString();
        this.inboundDuration = in.readString();
        this.inboundDate = in.readString();
        this.inboundFlights = in.readString();
        this.passengers = in.readInt();
        this.price = in.readString();
        long tmpCreationDate = in.readLong();
        this.creationDate = tmpCreationDate == -1 ? null : new Date(tmpCreationDate);
    }

    public static final Parcelable.Creator<FlightEntity> CREATOR = new Parcelable.Creator<FlightEntity>() {
        @Override
        public FlightEntity createFromParcel(Parcel source) {
            return new FlightEntity(source);
        }

        @Override
        public FlightEntity[] newArray(int size) {
            return new FlightEntity[size];
        }
    };
}
