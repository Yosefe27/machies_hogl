package com.retrotech.machies_hogl.models;

/**
 * Created by Harold King on 6/03/2019.
 */

public final class PaymentOptions {

    private int payment_option_id;
    private String payment_option_name;
    private String payment_option_image;

    public PaymentOptions() {
    }

    public PaymentOptions(int payment_option_id, String payment_option_name, String payment_option_image) {
        this.payment_option_id = payment_option_id;
        this.payment_option_name = payment_option_name;
        this.payment_option_image = payment_option_image;
    }

    public int getPayment_option_id() {
        return payment_option_id;
    }

    public void setPayment_option_id(int payment_option_id) {
        this.payment_option_id = payment_option_id;
    }

    public String getPayment_option_name() {
        return payment_option_name;
    }

    public void setPayment_option_name(String payment_option_name) {
        this.payment_option_name = payment_option_name;
    }

    public String getPayment_option_image() {
        return payment_option_image;
    }

    public void setPayment_option_image(String payment_option_image) {
        this.payment_option_image = payment_option_image;
    }
}
