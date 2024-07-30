package com.retrotech.machies_hogl.models;

public class MemberRegisterModel {

    private String group_name;
    private String group_id;
    private String firstname;
    private String lastname;
    private String nrc;
    private String password;
    private String admission_date;
    private String gender;
    private String ecap_hh_id;
    private String phone_number;
    private String user_role;
    private String single_female_caregiver;
    private String id;

    public MemberRegisterModel(String group_name, String group_id, String firstname, String lastname, String nrc, String password, String admission_date, String gender, String ecap_hh_id, String phone_number, String user_role, String single_female_caregiver, String id) {
        this.group_name = group_name;
        this.group_id = group_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.nrc = nrc;
        this.password = password;
        this.admission_date = admission_date;
        this.gender = gender;
        this.ecap_hh_id = ecap_hh_id;
        this.phone_number = phone_number;
        this.user_role = user_role;
        this.single_female_caregiver = single_female_caregiver;
        this.id = id;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdmission_date() {
        return admission_date;
    }

    public void setAdmission_date(String admission_date) {
        this.admission_date = admission_date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEcap_hh_id() {
        return ecap_hh_id;
    }

    public void setEcap_hh_id(String ecap_hh_id) {
        this.ecap_hh_id = ecap_hh_id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    public String getSingle_female_caregiver() {
        return single_female_caregiver;
    }

    public void setSingle_female_caregiver(String single_female_caregiver) {
        this.single_female_caregiver = single_female_caregiver;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
