package com.ovalion.mongoldorak.ovalion.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class CreditCard implements Parcelable {
    private String type;
    private String number;
    private String expiration;
    private String crypto;

    public CreditCard(){}

    public CreditCard(String type, String number, String expiration, String crypto) {
        this.type = type;
        this.number = number;
        this.expiration = expiration;
        this.crypto = crypto;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getCrypto() {
        return crypto;
    }

    public void setCrypto(String crypto) {
        this.crypto = crypto;
    }

    protected CreditCard(Parcel in) {
        type = in.readString();
        number = in.readString();
        expiration = in.readString();
        crypto = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(number);
        dest.writeString(expiration);
        dest.writeString(crypto);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<CreditCard> CREATOR = new Parcelable.Creator<CreditCard>() {
        @Override
        public CreditCard createFromParcel(Parcel in) {
            return new CreditCard(in);
        }

        @Override
        public CreditCard[] newArray(int size) {
            return new CreditCard[size];
        }
    };
}