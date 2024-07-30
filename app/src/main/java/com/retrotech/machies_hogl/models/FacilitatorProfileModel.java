package com.retrotech.machies_hogl.models;

public class FacilitatorProfileModel {
    private String
            facilitator_name,
            facilitator_id,
            gender,
            admission_date,
            group_name,
            number_groups,
            number_members,
            total_groups_contribution;

    public FacilitatorProfileModel(String facilitator_name, String facilitator_id, String gender, String admission_date, String group_name, String number_groups, String number_members, String total_groups_contribution) {
        this.facilitator_name = facilitator_name;
        this.facilitator_id = facilitator_id;
        this.gender = gender;
        this.admission_date = admission_date;
        this.group_name = group_name;
        this.number_groups = number_groups;
        this.number_members = number_members;
        this.total_groups_contribution = total_groups_contribution;
    }

    public String getFacilitator_name() {
        return facilitator_name;
    }

    public void setFacilitator_name(String facilitator_name) {
        this.facilitator_name = facilitator_name;
    }

    public String getFacilitator_id() {
        return facilitator_id;
    }

    public void setFacilitator_id(String facilitator_id) {
        this.facilitator_id = facilitator_id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAdmission_date() {
        return admission_date;
    }

    public void setAdmission_date(String admission_date) {
        this.admission_date = admission_date;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getNumber_groups() {
        return number_groups;
    }

    public void setNumber_groups(String number_groups) {
        this.number_groups = number_groups;
    }

    public String getNumber_members() {
        return number_members;
    }

    public void setNumber_members(String number_members) {
        this.number_members = number_members;
    }

    public String getTotal_groups_contribution() {
        return total_groups_contribution;
    }

    public void setTotal_groups_contribution(String total_groups_contribution) {
        this.total_groups_contribution = total_groups_contribution;
    }
}
