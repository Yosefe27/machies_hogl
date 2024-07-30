package com.retrotech.machies_hogl.models;

public class NewLoanRequestModel {
    private String member_name,
            member_id,
            gender,
            admission_date,
            group_name,
            total_savings,
            total_social_fund,
            fine_due,
            loan_due,
            interest_rate;

    public NewLoanRequestModel(String member_name, String member_id, String gender, String admission_date, String group_name, String total_savings, String total_social_fund, String fine_due, String loan_due,String interest_rate) {
        this.member_name = member_name;
        this.member_id = member_id;
        this.gender = gender;
        this.admission_date = admission_date;
        this.group_name = group_name;
        this.total_savings = total_savings;
        this.total_social_fund = total_social_fund;
        this.fine_due = fine_due;
        this.loan_due = loan_due;
        this.interest_rate = interest_rate;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
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

    public String getTotal_savings() {
        return total_savings;
    }

    public void setTotal_savings(String total_savings) {
        this.total_savings = total_savings;
    }

    public String getTotal_social_fund() {
        return total_social_fund;
    }

    public void setTotal_social_fund(String total_social_fund) {
        this.total_social_fund = total_social_fund;
    }

    public String getFine_due() {
        return fine_due;
    }

    public void setFine_due(String fine_due) {
        this.fine_due = fine_due;
    }

    public String getLoan_due() {
        return loan_due;
    }

    public void setLoan_due(String loan_due) {
        this.loan_due = loan_due;
    }

    public String getInterest_rate() {
        return interest_rate;
    }

    public void setInterest_rate(String interest_rate) {
        this.interest_rate = interest_rate;
    }
}
