package com.ovalion.mongoldorak.ovalion.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Competitor implements Parcelable {

    private String id;
    private String name;
    private String abbreviation;
    // home or away
    private boolean qualifier;
    private int rank;

    public Competitor(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean getQualifier() {
        return qualifier;
    }

    public void setQualifier(boolean qualifier) {
        this.qualifier = qualifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public boolean isQualifier() {
        return qualifier;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Competitor(Parcel in) {
        id = in.readString();
        name = in.readString();
        abbreviation = in.readString();
        qualifier = in.readByte() != 0x00;
        rank = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(abbreviation);
        dest.writeByte((byte) (qualifier ? 0x01 : 0x00));
        dest.writeInt(rank);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Competitor> CREATOR = new Parcelable.Creator<Competitor>() {
        @Override
        public Competitor createFromParcel(Parcel in) {
            return new Competitor(in);
        }

        @Override
        public Competitor[] newArray(int size) {
            return new Competitor[size];
        }
    };
}