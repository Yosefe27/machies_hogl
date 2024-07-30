package com.retrotech.machies_hogl.models;

public class DefaultDashboardModel {
    String nameType;
    int image;
    String cardNumber;

    public DefaultDashboardModel(String nameType, int image, String cardNumber) {
        this.nameType = nameType;
        this.image = image;
        this.cardNumber = cardNumber;
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

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
