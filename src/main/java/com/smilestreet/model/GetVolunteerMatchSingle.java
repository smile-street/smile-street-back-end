package com.smilestreet.model;

import java.util.Date;

public class GetVolunteerMatchSingle {

    public int vol_id;
    public String volunteer_id;
    public String firstname;
    public String lastname;
    public String contactnumber;
    public String username;
    public String employername;
    public String primarylocation;
    public int numberofdays;
    public Date startdate;
    public Date enddate;
    public boolean Web_Design;
    public boolean SEO;
    public boolean Graphic_Design;
    public boolean Teaching;
    public boolean Public_Health;
    public boolean Empowerment;
    public boolean Sports;
    public boolean Construction;
    public boolean Cooking;
    public boolean Accessibility;
    public boolean Mental_Health;
    public boolean Event_Planning;
    public boolean Gardening;
    public boolean Music;
    public boolean Dance;
    public int voln_id;

    public GetVolunteerMatchSingle(){

    }

    public GetVolunteerMatchSingle(int vol_id, String volunteer_id, String firstname, String lastname, String contactnumber, String username, String employername, String primarylocation, int numberofdays, Date startdate, Date enddate, boolean web_Design, boolean SEO, boolean graphic_Design, boolean teaching, boolean public_Health, boolean empowerment, boolean sports, boolean construction, boolean cooking, boolean accessibility, boolean mental_Health, boolean event_Planning, boolean gardening, boolean music, boolean dance, int voln_id) {
        this.vol_id = vol_id;
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
        this.voln_id = voln_id;
    }

    public GetVolunteerMatchSingle(int vol_id) {
    }

    public GetVolunteerMatchSingle(int vol_id, String volunteer_id, String firstname, String lastname, String contactnumber, String username, String employername, String primarylocation, int numberofdays, java.sql.Date datetime, java.sql.Date enddate, boolean web_design, boolean seo, boolean graphic_design, boolean teaching, boolean public_health, boolean empowerment, boolean sports, boolean construction, boolean cooking, boolean accessibility, boolean mental_health, boolean event_planning, boolean gardening, boolean music, boolean dance) {
    }

    public int getVol_id() {
        return vol_id;
    }

    public void setVol_id(int vol_id) {
        this.vol_id = vol_id;
    }

    public String getVolunteer_id() {
        return volunteer_id;
    }

    public void setVolunteer_id(String volunteer_id) {
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

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
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

    public int getVoln_id() {
        return voln_id;
    }

    public void setVoln_id(int voln_id) {
        this.voln_id = voln_id;
    }
}
