package com.example.googlemap;

public class Booking {
    public String userid;
    public String pitchid;
    public String ownerid;
    public String book_day;
    public String time_booking;
    public String start_time;
    public String end_time;
    public int verified;
    public String ticket;
    public float price;
    public String key;

    public Booking() {

    }

    public Booking(String userid,String book_day, String time_booking,
                   String start_time, String end_time, String pitchid, String ownerid, int verified,
                   String ticket, float price,String key
    ) {
        this.userid = userid;
        this.book_day = book_day;
        this.time_booking = time_booking;
        this.start_time = start_time;
        this.end_time = end_time;
        this.pitchid = pitchid;
        this.ownerid = ownerid;
        this.verified = verified;
        this.ticket = ticket;
        this.price = price;
        this.key = key;
    }
}
