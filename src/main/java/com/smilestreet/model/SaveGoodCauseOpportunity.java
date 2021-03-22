package com.smilestreet.model;

import java.sql.Date;


public class SaveGoodCauseOpportunity {


    private String opportunityname;
    private String opportunitydescription;
    private Date opportunitydate;
    private Date enddate;
    private String good_cause_id;

    public SaveGoodCauseOpportunity() {

    }

    public SaveGoodCauseOpportunity(String opportunityname, String opportunitydescription, Date opportunitydate, Date enddate, String good_cause_id) {
        this.opportunityname = opportunityname;
        this.opportunitydescription = opportunitydescription;
        this.opportunitydate = opportunitydate;
        this.enddate = enddate;
        this.good_cause_id = good_cause_id;
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

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getGood_cause_id() {
        return good_cause_id;
    }

    public void setGood_cause_id(String good_cause_id) {
        this.good_cause_id = good_cause_id;
    }
}
