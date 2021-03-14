
package com.smilestreet.model;

public class User {
    
    private int volunteerId;
    private String firstname;
    private String lastname;
    private String contactnumber;
    private String username;
    private String employername;
    private String primarylocation;
    private String numberofdays;
    private String startdate;
    private String enddate;

    public User(String firstname) {
        this.volunteerId = volunteerId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.contactnumber = contactnumber;
        this.username = username;
        this.employername = employername;
        this.primarylocation = primarylocation;
        this.numberofdays = numberofdays;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public int getVolunteerId() {
        return this.volunteerId;
    }

    public void setVolunteerId(int volunteerId) {
        this.volunteerId = volunteerId;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getContactnumber() {
        return this.contactnumber;
    }

    public void setContactnumber(String contactnumber) {
        this.contactnumber = contactnumber;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmployername() {
        return this.employername;
    }

    public void setEmployername(String employername) {
        this.employername = employername;
    }

    public String getPrimarylocation() {
        return this.primarylocation;
    }

    public void setPrimarylocation(String primarylocation) {
        this.primarylocation = primarylocation;
    }

    public String getNumberofdays() {
        return this.numberofdays;
    }

    public void setNumberofdays(String numberofdays) {
        this.numberofdays = numberofdays;
    }

    public String getStartdate() {
        return this.startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return this.enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }
    
}