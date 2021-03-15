
package com.smilestreet.model;
import java.sql.Date;


public class User {

    private int volunteer_id;
    private String firstname;
    private String lastname;
    private String contactnumber;
    private String username;
    private String employername;
    private String primarylocation;
    private int numberofdays;
    private String startdate;
    private String enddate;


    public User(int volunteer_id, String firstname, String lastname, String contactnumber, String username, String employername, String primarylocation, int numberofdays, String startdate, String enddate) {
        this.volunteer_id = volunteer_id;
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

    public int getVolunteer_id() {
        return volunteer_id;
    }

    public void setVolunteer_id(int volunteer_id) {
        this.volunteer_id = volunteer_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getContactnumber() {
        return contactnumber;
    }

    public void setContactnumber(String contactnumber) {
        this.contactnumber = contactnumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }
}