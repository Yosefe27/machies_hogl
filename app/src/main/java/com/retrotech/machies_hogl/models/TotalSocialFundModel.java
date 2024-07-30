package com.retrotech.machies_hogl.models;

public class TotalSocialFundModel {
    private String amount,group_name,total_disbursed,current_balance,group_id;

    public TotalSocialFundModel(String amount, String group_name, String total_disbursed, String current_balance, String group_id) {
        this.amount = amount;
        this.group_name = group_name;
        this.total_disbursed = total_disbursed;
        this.current_balance = current_balance;
        this.group_id = group_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getTotal_disbursed() {
        return total_disbursed;
    }

    public void setTotal_disbursed(String total_disbursed) {
        this.total_disbursed = total_disbursed;
    }

    public String getCurrent_balance() {
        return current_balance;
    }

    public void setCurrent_balance(String current_balance) {
        this.current_balance = current_balance;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }
}
