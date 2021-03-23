package com.smilestreet.model;

import java.util.Date;

public class GetVolunteerMatches {
    private String good_cause_opportunity_id;
    private String good_cause_name;
    private String opportnityname;
    private String opportunitydescription;
    private Date opportunitydates;

    public GetVolunteerMatches(){

    }

    public GetVolunteerMatches(String good_cause_opportunity_id, String good_cause_name, String opportnityname, String opportunitydescription, Date opportunitydates) {
        this.good_cause_opportunity_id = good_cause_opportunity_id;
        this.good_cause_name = good_cause_name;
        this.opportnityname = opportnityname;
        this.opportunitydescription = opportunitydescription;
        this.opportunitydates = opportunitydates;
    }

    public GetVolunteerMatches(String good_cause_opportunity_id) {
    }

    public String getGood_cause_opportunity_id() {
        return good_cause_opportunity_id;
    }

    public void setGood_cause_opportunity_id(String good_cause_opportunity_id) {
        this.good_cause_opportunity_id = good_cause_opportunity_id;
    }

    public String getGood_cause_name() {
        return good_cause_name;
    }

    public void setGood_cause_name(String good_cause_name) {
        this.good_cause_name = good_cause_name;
    }

    public String getOpportnityname() {
        return opportnityname;
    }

    public void setOpportnityname(String opportnityname) {
        this.opportnityname = opportnityname;
    }

    public String getOpportunitydescription() {
        return opportunitydescription;
    }

    public void setOpportunitydescription(String opportunitydescription) {
        this.opportunitydescription = opportunitydescription;
    }

    public Date getOpportunitydates() {
        return opportunitydates;
    }

    public void setOpportunitydates(Date opportunitydates) {
        this.opportunitydates = opportunitydates;
    }
}