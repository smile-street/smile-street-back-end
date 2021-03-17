
package com.smilestreet.model;

public class Volunteer {

    private int volunteer_id;
    private String firstname;
    private String lastname;
    private String username;
    private String contactnumber;


    public Volunteer() {

    }

    public Volunteer(int volunteer_id, String firstname, String lastname, String username, String contactnumber) {
        this.volunteer_id = volunteer_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.contactnumber = contactnumber;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContactnumber() {
        return contactnumber;
    }

    public void setContactnumber(String contactnumber) {
        this.contactnumber = contactnumber;
    }
}