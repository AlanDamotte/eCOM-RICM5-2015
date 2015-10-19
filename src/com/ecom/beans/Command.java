package com.ecom.beans;

public class Command {
    /* Propriétés du bean */
    private Client client;
    private String date;
    private Double amount;
    private String paymentMode;
    private String paymentStatus;
    private String deliveryMode;
    private String deliveryStatus;

    public Client getClient() {
        return client;
    }

    public void setClient( Client client ) {
        this.client = client;
    }

    public String getDate() {
        return date;
    }

    public void setDate( String date ) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount( Double amount ) {
        this.amount = amount;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode( String paymentMode ) {
        this.paymentMode = paymentMode;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus( String paymentStatus ) {
        this.paymentStatus = paymentStatus;
    }

    public String getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode( String deliveryMode ) {
        this.deliveryMode = deliveryMode;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus( String deliveryStatus ) {
        this.deliveryStatus = deliveryStatus;
    }
}
