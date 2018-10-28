package com.ovalion.mongoldorak.ovalion.Models;

import java.util.List;

public class Reservation
{
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
}
