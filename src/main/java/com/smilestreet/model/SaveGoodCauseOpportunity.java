package com.smilestreet.model;

import java.sql.Date;


public class SaveGoodCauseOpportunity {


    private String opportunityname;
    private String opportunitydescription;
    private Date opportunitydate;
   // private String good_cause_uid;

    public SaveGoodCauseOpportunity() {

    }

    public SaveGoodCauseOpportunity(String opportunityname, String opportunitydescription, Date opportunitydate) {
        this.opportunityname = opportunityname;
        this.opportunitydescription = opportunitydescription;
        this.opportunitydate = opportunitydate;
    }

    public String getOpportunityname() {
        return opportunityname;
    }

    public void setOpportunityname(String opportunityname) {
        this.opportunityname = opportunityname;
    }

    public String getOpportunitydescription() {
        return opportunitydescription;
    }

    public void setOpportunitydescription(String opportunitydescription) {
        this.opportunitydescription = opportunitydescription;
    }

    public Date getOpportunitydate() {
        return opportunitydate;
    }

    public void setOpportunitydate(Date opportunitydate) {
        this.opportunitydate = opportunitydate;
    }
}