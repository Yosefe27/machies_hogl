package com.retrotech.machies_hogl.models;

public class ViewTotalFinesModel {
    private String full_name;
    private String member_id;
    private String charge_amount;
    private String post_amount;
    private String fine_balance;

    public ViewTotalFinesModel(String full_name, String member_id, String charge_amount, String post_amount, String fine_balance) {
        this.full_name = full_name;
        this.member_id = member_id;
        this.charge_amount = charge_amount;
        this.post_amount = post_amount;
        this.fine_balance = fine_balance;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getCharge_amount() {
        return charge_amount;
    }

    public void setCharge_amount(String charge_amount) {
        this.charge_amount = charge_amount;
    }

    public String getPost_amount() {
        return post_amount;
    }

    public void setPost_amount(String post_amount) {
        this.post_amount = post_amount;
    }

    public String getFine_balance() {
        return fine_balance;
    }

    public void setFine_balance(String fine_balance) {
        this.fine_balance = fine_balance;
    }
}
