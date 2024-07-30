package com.retrotech.machies_hogl.models;

public class LoanRequests {

    private String id;
    private String amount;
    private String interest_rate;
    private String interest_amount;
    private String total_amount_due;
    private String contributor_id;
    private String full_name;
    private String group_id;
    private String date_created;
    private String loan_date;

    public LoanRequests(String id, String amount, String interest_rate, String interest_amount, String total_amount_due, String contributor_id, String full_name, String group_id, String date_created, String loan_date) {
        this.id = id;
        this.amount = amount;
        this.interest_rate = interest_rate;
        this.interest_amount = interest_amount;
        this.total_amount_due = total_amount_due;
        this.contributor_id = contributor_id;
        this.full_name = full_name;
        this.group_id = group_id;
        this.date_created = date_created;
        this.loan_date = loan_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getInterest_rate() {
        return interest_rate;
    }

    public void setInterest_rate(String interest_rate) {
        this.interest_rate = interest_rate;
    }

    public String getInterest_amount() {
        return interest_amount;
    }

    public void setInterest_amount(String interest_amount) {
        this.interest_amount = interest_amount;
    }

    public String getTotal_amount_due() {
        return total_amount_due;
    }

    public void setTotal_amount_due(String total_amount_due) {
        this.total_amount_due = total_amount_due;
    }

    public String getContributor_id() {
        return contributor_id;
    }

    public void setContributor_id(String contributor_id) {
        this.contributor_id = contributor_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
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

    public String getLoan_date() {
        return loan_date;
    }

    public void setLoan_date(String loan_date) {
        this.loan_date = loan_date;
    }
}
