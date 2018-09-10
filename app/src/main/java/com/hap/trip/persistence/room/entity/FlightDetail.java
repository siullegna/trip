package com.hap.trip.persistence.room.entity;

public class FlightDetail {
    private final String time;
    private final String aircraft;
    private final String duration;
    private final String classType;
    private final String flightNumber;
    private final int sequence;
    private final String type;

    public FlightDetail(String time, String aircraft, String duration, String classType, String flightNumber, int sequence, final DetailType detailType) {
        this.time = time;
        this.aircraft = aircraft;
        this.duration = duration;
        this.classType = classType;
        this.flightNumber = flightNumber;
        this.sequence = sequence;
        this.type = detailType.detailType;
    }

    public String getTime() {
        return time;
    }

    public String getAircraft() {
        return aircraft;
    }

    public String getDuration() {
        return duration;
    }

    public String getClassType() {
        return classType;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public int getSequence() {
        return sequence;
    }

    public String getType() {
        return type;
    }

    public enum DetailType {
        OUTBOUND("OUTBOUND"),
        INBOUND("INBOUND"),
        UNKNOWN("UNKNOWN");

        private final String detailType;

        DetailType(String detailType) {
            this.detailType = detailType;
        }

        public String getDetailType() {
            return detailType;
        }

        public static DetailType fromType(final String type) {
            DetailType detailType = UNKNOWN;

            for (final DetailType current : values()) {
                if (current.detailType.equals(type)) {
                    detailType = current;
                    break;
                }
            }

            return detailType;
        }
    }
}
