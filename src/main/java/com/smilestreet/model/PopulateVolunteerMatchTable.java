package com.smilestreet.model;

public class PopulateVolunteerMatchTable {
    private int voln_id;
    private int join_id;
    private Boolean matched;

    public PopulateVolunteerMatchTable(int voln_id, int join_id, Boolean matched) {
        this.voln_id = voln_id;
        this.join_id = join_id;
        this.matched = matched;
    }

    public PopulateVolunteerMatchTable()
    {

    }

    public int getVoln_id() {
        return voln_id;
    }

    public void setVoln_id(int voln_id) {
        this.voln_id = voln_id;
    }

    public int getJoin_id() {
        return join_id;
    }

    public void setJoin_id(int join_id) {
        this.join_id = join_id;
    }

    public Boolean getMatched() {
        return matched;
    }

    public void setMatched(Boolean matched) {
        this.matched = matched;
    }
}


