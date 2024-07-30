package com.retrotech.machies_hogl.models;

public class GroupAdminModel {
    String nameType;
    int image;
    int color;
    String cardID;

    public GroupAdminModel(String nameType, int image, int color, String cardID) {
        this.nameType = nameType;
        this.image = image;
        this.color = color;
        this.cardID = cardID;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }
}
