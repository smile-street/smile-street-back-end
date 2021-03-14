package com.smilestreet.model;

public class Skill {
    private int skill_id;
    private String skillname;

    public Skill(int skill_id, String skillname) {
        this.skill_id = skill_id;
        this.skillname = skillname;
    }

    public int getSkill_id() {
        return skill_id;
    }

    public String getSkillname() {
        return skillname;
    }
}
