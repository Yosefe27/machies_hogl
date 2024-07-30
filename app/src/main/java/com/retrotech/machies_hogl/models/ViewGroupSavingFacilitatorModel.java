package com.retrotech.machies_hogl.models;

public class ViewGroupSavingFacilitatorModel {
    String  group_name,group_id,total_contribution;

    public ViewGroupSavingFacilitatorModel(String group_name, String group_id, String total_contribution) {
        this.group_name = group_name;
        this.group_id = group_id;
        this.total_contribution = total_contribution;
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

    public String getTotal_contribution() {
        return total_contribution;
    }

    public void setTotal_contribution(String total_contribution) {
        this.total_contribution = total_contribution;
    }
}
