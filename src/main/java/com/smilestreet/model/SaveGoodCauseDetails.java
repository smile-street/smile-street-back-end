package com.smilestreet.model;

public class SaveGoodCauseDetails {

    private String good_cause_name;
    private String descriptionofgoodcause;

    public SaveGoodCauseDetails() {

    }

    public SaveGoodCauseDetails(String good_cause_name, String descriptionofgoodcause) {
        this.good_cause_name = good_cause_name;
        this.descriptionofgoodcause = descriptionofgoodcause;
    }

    public String getGood_cause_name() {
        return good_cause_name;
    }

    public void setGood_cause_name(String good_cause_name) {
        this.good_cause_name = good_cause_name;
    }

    public String getDescriptionofgoodcause() {
        return descriptionofgoodcause;
    }

    public void setDescriptionofgoodcause(String descriptionofgoodcause) {
        this.descriptionofgoodcause = descriptionofgoodcause;
    }
}


