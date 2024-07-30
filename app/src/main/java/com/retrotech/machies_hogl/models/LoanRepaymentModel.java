package com.retrotech.machies_hogl.models;

public class LoanRepaymentModel {
    private String id;
    private String loan_id;
    private String amount;
    private String contributor_id;
    private String group_id;
    private String date_created;
    private String full_name;

    public LoanRepaymentModel(String id, String loan_id, String amount, String contributor_id, String group_id, String date_created, String full_name) {
        this.id = id;
        this.loan_id = loan_id;
        this.amount = amount;
        this.contributor_id = contributor_id;
        this.group_id = group_id;
        this.date_created = date_created;
        this.full_name = full_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoan_id() {
        return loan_id;
    }

    public void setLoan_id(String loan_id) {
        this.loan_id = loan_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getContributor_id() {
        return contributor_id;
    }

    public void setContributor_id(String contributor_id) {
        this.contributor_id = contributor_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }
}
