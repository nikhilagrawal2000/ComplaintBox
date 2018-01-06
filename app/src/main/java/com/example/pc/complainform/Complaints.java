package com.example.pc.complainform;

/**
 * Created by Dell on 03-01-2018.
 */

public class Complaints {
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    String time;
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComplaint_type() {
        return complaint_type;
    }

    public void setComplaint_type(String complaint_type) {
        this.complaint_type = complaint_type;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    String date;
    String complaint_type;

    public Complaints(String date, String complaint_type, String complaint) {
        this.date = date;
        this.complaint_type = complaint_type;
        this.complaint = complaint;
    }

    String complaint;
}
