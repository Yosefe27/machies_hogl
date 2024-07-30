package com.retrotech.machies_hogl.models;

public class MainActivityModel {
    String nameType;
    int imageView;
    int color;
    int cardNumber;

    public MainActivityModel(String nameType, int imageView, int color, int cardNumber) {
        this.nameType = nameType;
        this.imageView = imageView;
        this.color = color;
        this.cardNumber = cardNumber;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }
}
