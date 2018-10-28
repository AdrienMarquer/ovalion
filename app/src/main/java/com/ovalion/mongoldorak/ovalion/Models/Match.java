package com.ovalion.mongoldorak.ovalion.Models;

import android.os.Parcel;
import android.os.Parcelable;
public class Match implements Parcelable {
    private String id ;
    private String date;
    private String time;
    private boolean status;
    private String map_coordinates;
    private Competitor competitorA;
    private Competitor competitorB;

    public Match(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMap_coordinates() {
        return map_coordinates;
    }

    public void setMap_coordinates(String map_coordinates) {
        this.map_coordinates = map_coordinates;
    }

    public Competitor getCompetitorA() {
        return competitorA;
    }

    public void setCompetitorA(Competitor competitorA) {
        this.competitorA = competitorA;
    }

    public Competitor getCompetitorB() {
        return competitorB;
    }

    public void setCompetitorB(Competitor competitorB) {
        this.competitorB = competitorB;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isStatus() {
        return status;
    }

    protected Match(Parcel in) {
        id = in.readString();
        date = in.readString();
        time = in.readString();
        status = in.readByte() != 0x00;
        map_coordinates = in.readString();
        competitorA = (Competitor) in.readValue(Competitor.class.getClassLoader());
        competitorB = (Competitor) in.readValue(Competitor.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(date);
        dest.writeString(time);
        dest.writeByte((byte) (status ? 0x01 : 0x00));
        dest.writeString(map_coordinates);
        dest.writeValue(competitorA);
        dest.writeValue(competitorB);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Match> CREATOR = new Parcelable.Creator<Match>() {
        @Override
        public Match createFromParcel(Parcel in) {
            return new Match(in);
        }

        @Override
        public Match[] newArray(int size) {
            return new Match[size];
        }
    };
}