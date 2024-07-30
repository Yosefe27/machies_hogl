package com.retrotech.machies_hogl.models;

public class FacilitatorRegisterModel {
    private String
            group_id,
            group_name,
            register;

    public FacilitatorRegisterModel(String group_id, String group_name, String register) {
        this.group_id = group_id;
        this.group_name = group_name;
        this.register = register;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }
}
