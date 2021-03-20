package com.smilestreet.model;

public class SaveGoodCause {

    private String firstname;
    private String lastname;
    private String contactnumber;
    private String emailaddress;

    public SaveGoodCause(){

    }
    public SaveGoodCause(String firstname, String lastname, String contactnumber, String emailaddress) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.contactnumber = contactnumber;
        this.emailaddress = emailaddress;
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

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }
}
