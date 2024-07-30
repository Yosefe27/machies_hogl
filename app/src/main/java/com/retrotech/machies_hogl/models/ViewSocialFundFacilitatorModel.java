package com.retrotech.machies_hogl.models;

public class ViewSocialFundFacilitatorModel {
    String total_social_in,total_social_out,social_fund_balance,group_name,group_id;

    public ViewSocialFundFacilitatorModel(String total_social_in, String total_social_out, String social_fund_balance, String group_name, String group_id) {
        this.total_social_in = total_social_in;
        this.total_social_out = total_social_out;
        this.social_fund_balance = social_fund_balance;
        this.group_name = group_name;
        this.group_id = group_id;
    }

    public String getTotal_social_in() {
        return total_social_in;
    }

    public void setTotal_social_in(String total_social_in) {
        this.total_social_in = total_social_in;
    }

    public String getTotal_social_out() {
        return total_social_out;
    }

    public void setTotal_social_out(String total_social_out) {
        this.total_social_out = total_social_out;
    }

    public String getSocial_fund_balance() {
        return social_fund_balance;
    }

    public void setSocial_fund_balance(String social_fund_balance) {
        this.social_fund_balance = social_fund_balance;
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
}
