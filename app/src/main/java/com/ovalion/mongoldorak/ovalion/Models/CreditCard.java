package com.ovalion.mongoldorak.ovalion.Models;

public class CreditCard
{
    private String type;
    private String number;
    private String expiration;
    private String crypto;

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
}
