package com.ovalion.mongoldorak.ovalion.Models;

public class ReservationBDD {
    private int id;
    private String home_team;
    private String away_team;
    private String location;
    private String departure;
    private String departure_id;
    private String departure_gps;
    private String return_;
    private String return_id;
    private String return_gps;
    private String hostel;
    private String date;
    private String hour;
    private double price;

    public ReservationBDD(int id,String home_team, String away_team, String location, String departure, String departure_id,
                          String departure_gps, String return_, String return_id, String return_gps, String hostel,String date,String hour) {
        this.id = id;
        this.home_team = home_team;
        this.away_team = away_team;
        this.location = location;
        this.departure = departure;
        this.departure_id = departure_id;
        this.departure_gps = departure_gps;
        this.return_ = return_;
        this.return_id = return_id;
        this.return_gps = return_gps;
        this.hostel = hostel;
        this.date = date;
        this.hour = hour;
    }

    public ReservationBDD(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHome_team() {
        return home_team;
    }

    public void setHome_team(String home_team) {
        this.home_team = home_team;
    }

    public String getAway_team() {
        return away_team;
    }

    public void setAway_team(String away_team) {
        this.away_team = away_team;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDeparture_id() {
        return departure_id;
    }

    public void setDeparture_id(String departure_id) {
        this.departure_id = departure_id;
    }

    public String getDeparture_gps() {
        return departure_gps;
    }

    public void setDeparture_gps(String departure_gps) {
        this.departure_gps = departure_gps;
    }

    public String getReturn_() {
        return return_;
    }

    public void setReturn_(String return_) {
        this.return_ = return_;
    }

    public String getReturn_id() {
        return return_id;
    }

    public void setReturn_id(String return_id) {
        this.return_id = return_id;
    }

    public String getReturn_gps() {
        return return_gps;
    }

    public void setReturn_gps(String return_gps) {
        this.return_gps = return_gps;
    }

    public String getHostel() {
        return hostel;
    }

    public void setHostel(String hostel) {
        this.hostel = hostel;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
