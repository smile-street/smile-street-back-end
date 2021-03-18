
package com.smilestreet.model;


public class Volunteer {

    //private int volunteer_id;
    private String firstname;
    private String lastname;
    private String contactnumber;
    private String username;

    public Volunteer(String firstname, String lastname, String contactnumber, String username) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.contactnumber = contactnumber;
        this.username = username;
    }
    public  Volunteer(){

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
}