package com.ovalion.mongoldorak.ovalion.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Reservation implements Parcelable {
    private int id;
    private Match match;
    private CreditCard cb;
    private BusTrip busGo;
    private BusTrip busBack;
    private String bustripType;
    private String ticketType;
    private String hostel;
    private String location;


    public Reservation(){}

    public Reservation(Match match, String bustripType, String ticketType) {
        this.match = match;
        this.bustripType = bustripType;
        this.ticketType = ticketType;
    }

    public Reservation(Match match, CreditCard cb, BusTrip busGo, BusTrip busBack, String bustripType, String ticketType) {
        this.match = match;
        this.cb = cb;
        this.busGo = busGo;
        this.busBack = busBack;
        this.bustripType = bustripType;
        this.ticketType = ticketType;
    }

    public String getBustripType() {
        return bustripType;
    }

    public void setBustripType(String bustripType) {
        this.bustripType = bustripType;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public CreditCard getCb() {
        return cb;
    }

    public void setCb(CreditCard cb) {
        this.cb = cb;
    }

    public BusTrip getBusGo() {
        return busGo;
    }

    public void setBusGo(BusTrip busGo) {
        this.busGo = busGo;
    }

    public BusTrip getBusBack() {
        return busBack;
    }

    public void setBusBack(BusTrip busBack) {
        this.busBack = busBack;
    }

    public String getHostel() {
        return hostel;
    }

    public void setHostel(String hostel) {
        this.hostel = hostel;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    protected Reservation(Parcel in) {
        id = in.readInt();
        match = (Match) in.readValue(Match.class.getClassLoader());
        cb = (CreditCard) in.readValue(CreditCard.class.getClassLoader());
        busGo = (BusTrip) in.readValue(BusTrip.class.getClassLoader());
        busBack = (BusTrip) in.readValue(BusTrip.class.getClassLoader());
        bustripType = in.readString();
        ticketType = in.readString();
        hostel = in.readString();
        location = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeValue(match);
        dest.writeValue(cb);
        dest.writeValue(busGo);
        dest.writeValue(busBack);
        dest.writeString(bustripType);
        dest.writeString(ticketType);
        dest.writeString(hostel);
        dest.writeString(location);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Reservation> CREATOR = new Parcelable.Creator<Reservation>() {
        @Override
        public Reservation createFromParcel(Parcel in) {
            return new Reservation(in);
        }

        @Override
        public Reservation[] newArray(int size) {
            return new Reservation[size];
        }
    };
}