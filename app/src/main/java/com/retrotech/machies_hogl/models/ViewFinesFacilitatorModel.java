package com.retrotech.machies_hogl.models;

public class ViewFinesFacilitatorModel {
    String total_fine_charged,total_fine_paid,fines_balance,group_name,group_id;

    public ViewFinesFacilitatorModel(String total_fine_charged, String total_fine_paid, String fines_balance, String group_name, String group_id) {
        this.total_fine_charged = total_fine_charged;
        this.total_fine_paid = total_fine_paid;
        this.fines_balance = fines_balance;
        this.group_name = group_name;
        this.group_id = group_id;
    }

    public String getTotal_fine_charged() {
        return total_fine_charged;
    }

    public void setTotal_fine_charged(String total_fine_charged) {
        this.total_fine_charged = total_fine_charged;
    }

    public String getTotal_fine_paid() {
        return total_fine_paid;
    }

    public void setTotal_fine_paid(String total_fine_paid) {
        this.total_fine_paid = total_fine_paid;
    }

    public String getFines_balance() {
        return fines_balance;
    }

    public void setFines_balance(String fines_balance) {
        this.fines_balance = fines_balance;
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
