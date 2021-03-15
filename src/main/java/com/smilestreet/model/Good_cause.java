package com.smilestreet.model;

public class Good_cause {
    private int good_cause_id;
    private String descriptionofgoodcause;
    private String firstname;
    private String lastname;
    private String emailaddress;
    private String contactnumber;

    public Good_cause(int good_cause_id, String descriptionofgoodcause, String firstname, String lastname, String emailaddress, String contactnumber) {
        this.good_cause_id = good_cause_id;
        this.descriptionofgoodcause = descriptionofgoodcause;
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailaddress = emailaddress;
        this.contactnumber = contactnumber;
    }

    public int getGood_cause_id() {
        return good_cause_id;
    }

    public String getDescriptionofgoodcause() {
        return descriptionofgoodcause;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public String getContactnumber() {
        return contactnumber;
    }

    public void setGood_cause_id(int good_cause_id) {
        this.good_cause_id = good_cause_id;
    }

    public void setDescriptionofgoodcause(String descriptionofgoodcause) {
        this.descriptionofgoodcause = descriptionofgoodcause;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public void setContactnumber(String contactnumber) {
        this.contactnumber = contactnumber;
    }
}
