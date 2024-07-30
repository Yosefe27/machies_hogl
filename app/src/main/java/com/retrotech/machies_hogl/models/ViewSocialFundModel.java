package com.retrotech.machies_hogl.models;

public class ViewSocialFundModel {
    private String amount;
    private String full_name;
    private String id;
    private String month_contributed_for;

    public ViewSocialFundModel(String amount, String full_name, String id, String month_contributed_for) {
        this.amount = amount;
        this.full_name = full_name;
        this.id = id;
        this.month_contributed_for = month_contributed_for;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMonth_contributed_for() {
        return month_contributed_for;
    }

    public void setMonth_contributed_for(String month_contributed_for) {
        this.month_contributed_for = month_contributed_for;
    }
}
