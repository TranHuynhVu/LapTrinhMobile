package com.example.contentprovider;

public class Sms {
    private final String address;
    private final String body;
    private final String date;

    public Sms(String address, String body, String date) {
        this.address = address;
        this.body = body;
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public String getBody() {
        return body;
    }

    public String getDate() {
        return date;
    }
}
