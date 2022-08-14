package com.android.vnnv.model;

public class Order {
    private String address;
    private String market;
    private String price;
    private String status;

    public Order() {
    }

    public Order(String address, String market, String price, String status) {
        this.address = address;
        this.market = market;
        this.price = price;
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public String getPrice() {return price;}

    public String getMarket() {return market;}

    public String getStatus() {return status;}

    public void setStatus(String status) {
        this.status = status;
    }
}