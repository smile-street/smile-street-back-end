package com.smilestreet.model;

import java.sql.Date;

public class VolunteerAvailability {
    private String employername;
    private String primarylocation;
    private int numberofdays;
    private Date startdate;
    private  Date enddate;

    public VolunteerAvailability(){

    }
    public VolunteerAvailability(String employername, String primarylocation, int numberofdays, Date startdate, Date enddate) {
        this.employername = employername;
        this.primarylocation = primarylocation;
        this.numberofdays = numberofdays;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public String getEmployername() {
        return employername;
    }

    public void setEmployername(String employername) {
        this.employername = employername;
    }

    public String getPrimarylocation() {
        return primarylocation;
    }

    public void setPrimarylocation(String primarylocation) {
        this.primarylocation = primarylocation;
    }

    public int getNumberofdays() {
        return numberofdays;
    }

    public void setNumberofdays(int numberofdays) {
        this.numberofdays = numberofdays;
    }

    public java.sql.Date getStartdate() {
        return (java.sql.Date) startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }
}
