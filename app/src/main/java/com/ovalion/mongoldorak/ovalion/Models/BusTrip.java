package com.ovalion.mongoldorak.ovalion.Models;

public class BusTrip
{
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
}
