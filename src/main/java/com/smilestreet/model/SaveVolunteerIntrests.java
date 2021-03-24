package com.smilestreet.model;

public class SaveVolunteerIntrests {
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

    public SaveVolunteerIntrests(boolean web_Design, boolean SEO, boolean graphic_Design, boolean teaching, boolean public_Health, boolean empowerment, boolean sports, boolean construction, boolean cooking, boolean accessibility, boolean mental_Health, boolean event_Planning, boolean gardening, boolean music, boolean dance) {
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
}
