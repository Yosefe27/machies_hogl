package com.retrotech.machies_hogl.models;

public class Groups {
    private String id;
    private String group_name;
    private String date_created;
    private String annual_interest_rate;
    private String cycle_number;
    private String first_training_meeting_date;
    private String date_savings_started;
    private String reinvested_savings_cycle_start;
    private String registered_members_cycle_start;
    private String group_management_spinner;
    private String status;

    public Groups() {
    }

    public Groups(String id, String group_name, String date_created, String annual_interest_rate, String cycle_number, String first_training_meeting_date, String date_savings_started, String reinvested_savings_cycle_start, String registered_members_cycle_start, String group_management_spinner, String status) {
        this.id = id;
        this.group_name = group_name;
        this.date_created = date_created;
        this.annual_interest_rate = annual_interest_rate;
        this.cycle_number = cycle_number;
        this.first_training_meeting_date = first_training_meeting_date;
        this.date_savings_started = date_savings_started;
        this.reinvested_savings_cycle_start = reinvested_savings_cycle_start;
        this.registered_members_cycle_start = registered_members_cycle_start;
        this.group_management_spinner = group_management_spinner;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getAnnual_interest_rate() {
        return annual_interest_rate;
    }

    public void setAnnual_interest_rate(String annual_interest_rate) {
        this.annual_interest_rate = annual_interest_rate;
    }

    public String getCycle_number() {
        return cycle_number;
    }

    public void setCycle_number(String cycle_number) {
        this.cycle_number = cycle_number;
    }

    public String getFirst_training_meeting_date() {
        return first_training_meeting_date;
    }

    public void setFirst_training_meeting_date(String first_training_meeting_date) {
        this.first_training_meeting_date = first_training_meeting_date;
    }

    public String getDate_savings_started() {
        return date_savings_started;
    }

    public void setDate_savings_started(String date_savings_started) {
        this.date_savings_started = date_savings_started;
    }

    public String getReinvested_savings_cycle_start() {
        return reinvested_savings_cycle_start;
    }

    public void setReinvested_savings_cycle_start(String reinvested_savings_cycle_start) {
        this.reinvested_savings_cycle_start = reinvested_savings_cycle_start;
    }

    public String getRegistered_members_cycle_start() {
        return registered_members_cycle_start;
    }

    public void setRegistered_members_cycle_start(String registered_members_cycle_start) {
        this.registered_members_cycle_start = registered_members_cycle_start;
    }

    public String getGroup_management_spinner() {
        return group_management_spinner;
    }

    public void setGroup_management_spinner(String group_management_spinner) {
        this.group_management_spinner = group_management_spinner;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
