package com.smilestreet.model;

import java.util.Date;

public class GetVolunteerMatchesOpportunityObject {

    private String good_cause_opportunity_id;
    private String opportunityname;
    private Date opportunitydate;
    private String opportunitydescription;
    private String good_cause_uid;
    private int joining_id;
    private boolean Web_Design;
    private boolean SEO;
    private boolean Graphic_Design;
    private boolean Teaching;
    private boolean Public_Health;
    private boolean Empowerment;
    private boolean Sports;
    private boolean Construction;
    private boolean Cooking;
    private boolean Accessibility;
    private boolean Mental_Health;
    private boolean Event_Planning;
    private boolean Gardening;
    private boolean Music;
    private boolean Dance;
    private String Location;
    private int gc_id1;
    private int vol_id;

    public GetVolunteerMatchesOpportunityObject() {


    }

    public GetVolunteerMatchesOpportunityObject(String good_cause_opportunity_id, String opportunityname, Date opportunitydate, String opportunitydescription, String good_cause_uid, int joining_id, boolean web_Design, boolean SEO, boolean graphic_Design, boolean teaching, boolean public_Health, boolean empowerment, boolean sports, boolean construction, boolean cooking, boolean accessibility, boolean mental_Health, boolean event_Planning, boolean gardening, boolean music, boolean dance, String location, int gc_id1, int vol_id) {
        this.good_cause_opportunity_id = good_cause_opportunity_id;
        this.opportunityname = opportunityname;
        this.opportunitydate = opportunitydate;
        this.opportunitydescription = opportunitydescription;
        this.good_cause_uid = good_cause_uid;
        this.joining_id = joining_id;
        Web_Design = web_Design;
        this.SEO = SEO;
        Graphic_Design = graphic_Design;
        Teaching = teaching;
        Public_Health = public_Health;
        Empowerment = empowerment;
        Sports = sports;
        Construction = construction;
        Cooking = cooking;
        Accessibility = accessibility;
        Mental_Health = mental_Health;
        Event_Planning = event_Planning;
        Gardening = gardening;
        Music = music;
        Dance = dance;
        Location = location;
        this.gc_id1 = gc_id1;
        this.vol_id = vol_id;

    }

    public String getGood_cause_opportunity_id() {
        return good_cause_opportunity_id;
    }

    public void setGood_cause_opportunity_id(String good_cause_opportunity_id) {
        this.good_cause_opportunity_id = good_cause_opportunity_id;
    }

    public String getOpportunityname() {
        return opportunityname;
    }

    public void setOpportunityname(String opportunityname) {
        this.opportunityname = opportunityname;
    }

    public Date getOpportunitydate() {
        return opportunitydate;
    }

    public void setOpportunitydate(Date opportunitydate) {
        this.opportunitydate = opportunitydate;
    }

    public String getOpportunitydescription() {
        return opportunitydescription;
    }

    public void setOpportunitydescription(String opportunitydescription) {
        this.opportunitydescription = opportunitydescription;
    }

    public String getGood_cause_uid() {
        return good_cause_uid;
    }

    public void setGood_cause_uid(String good_cause_uid) {
        this.good_cause_uid = good_cause_uid;
    }

    public int getJoining_id() {
        return joining_id;
    }

    public void setJoining_id(int joining_id) {
        this.joining_id = joining_id;
    }

    public boolean isWeb_Design() {
        return Web_Design;
    }

    public void setWeb_Design(boolean web_Design) {
        Web_Design = web_Design;
    }

    public boolean isSEO() {
        return SEO;
    }

    public void setSEO(boolean SEO) {
        this.SEO = SEO;
    }

    public boolean isGraphic_Design() {
        return Graphic_Design;
    }

    public void setGraphic_Design(boolean graphic_Design) {
        Graphic_Design = graphic_Design;
    }

    public boolean isTeaching() {
        return Teaching;
    }

    public void setTeaching(boolean teaching) {
        Teaching = teaching;
    }

    public boolean isPublic_Health() {
        return Public_Health;
    }

    public void setPublic_Health(boolean public_Health) {
        Public_Health = public_Health;
    }

    public boolean isEmpowerment() {
        return Empowerment;
    }

    public void setEmpowerment(boolean empowerment) {
        Empowerment = empowerment;
    }

    public boolean isSports() {
        return Sports;
    }

    public void setSports(boolean sports) {
        Sports = sports;
    }

    public boolean isConstruction() {
        return Construction;
    }

    public void setConstruction(boolean construction) {
        Construction = construction;
    }

    public boolean isCooking() {
        return Cooking;
    }

    public void setCooking(boolean cooking) {
        Cooking = cooking;
    }

    public boolean isAccessibility() {
        return Accessibility;
    }

    public void setAccessibility(boolean accessibility) {
        Accessibility = accessibility;
    }

    public boolean isMental_Health() {
        return Mental_Health;
    }

    public void setMental_Health(boolean mental_Health) {
        Mental_Health = mental_Health;
    }

    public boolean isEvent_Planning() {
        return Event_Planning;
    }

    public void setEvent_Planning(boolean event_Planning) {
        Event_Planning = event_Planning;
    }

    public boolean isGardening() {
        return Gardening;
    }

    public void setGardening(boolean gardening) {
        Gardening = gardening;
    }

    public boolean isMusic() {
        return Music;
    }

    public void setMusic(boolean music) {
        Music = music;
    }

    public boolean isDance() {
        return Dance;
    }

    public void setDance(boolean dance) {
        Dance = dance;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public int getGc_id1() {
        return gc_id1;
    }

    public void setGc_id1(int gc_id1) {
        this.gc_id1 = gc_id1;
    }

    public int getVol_id() {
        return vol_id;
    }

    public void setVol_id(int vol_id) {
        this.vol_id = vol_id;
    }
}