package com.retrotech.machies_hogl.models;

public class MemberRegisterListModel {
    private String firstname;
    private String lastname;
    private String register;
    private String date;
    private String id;

    public MemberRegisterListModel(String firstname, String lastname, String register, String date, String id) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.register = register;
        this.date = date;
        this.id = id;
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

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
