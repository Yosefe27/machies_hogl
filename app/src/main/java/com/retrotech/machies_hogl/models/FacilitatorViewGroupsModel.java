package com.retrotech.machies_hogl.models;

public class FacilitatorViewGroupsModel {
    private String
            group_name,
            group_id,
            member_name;
//            member_id,
//            member_contribution;


    public FacilitatorViewGroupsModel(String group_name, String group_id, String member_name) {
        this.group_name = group_name;
        this.group_id = group_id;
        this.member_name = member_name;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }
}
