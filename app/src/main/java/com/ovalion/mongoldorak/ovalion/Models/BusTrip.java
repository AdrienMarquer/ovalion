package com.ovalion.mongoldorak.ovalion.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class BusTrip implements Parcelable {
    private String id;
    private String distance;
    private String date;
    private String cost;
    private String departure_adress;
    private String departure_gps;
    private String arrival_adress;
    private String arrival_gps;

    public BusTrip(){}

    public BusTrip(String distance, String date, String cost, String departure_adress, String arrival_adress) {
        this.distance = distance;
        this.date = date;
        this.cost = cost;
        this.departure_adress = departure_adress;
        this.arrival_adress = arrival_adress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDeparture_adress() {
        return departure_adress;
    }

    public void setDeparture_adress(String departure_adress) {
        this.departure_adress = departure_adress;
    }

    public String getArrival_adress() {
        return arrival_adress;
    }

    public void setArrival_adress(String arrival_adress) {
        this.arrival_adress = arrival_adress;
    }

    public String getDeparture_gps() {
        return departure_gps;
    }

    public void setDeparture_gps(String departure_gps) {
        this.departure_gps = departure_gps;
    }

    public String getArrival_gps() {
        return arrival_gps;
    }

    public void setArrival_gps(String arrival_gps) {
        this.arrival_gps = arrival_gps;
    }

    protected BusTrip(Parcel in) {
        id = in.readString();
        distance = in.readString();
        date = in.readString();
        cost = in.readString();
        departure_adress = in.readString();
        departure_gps = in.readString();
        arrival_adress = in.readString();
        arrival_gps = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(distance);
        dest.writeString(date);
        dest.writeString(cost);
        dest.writeString(departure_adress);
        dest.writeString(departure_gps);
        dest.writeString(arrival_adress);
        dest.writeString(arrival_gps);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<BusTrip> CREATOR = new Parcelable.Creator<BusTrip>() {
        @Override
        public BusTrip createFromParcel(Parcel in) {
            return new BusTrip(in);
        }

        @Override
        public BusTrip[] newArray(int size) {
            return new BusTrip[size];
        }
    };
}