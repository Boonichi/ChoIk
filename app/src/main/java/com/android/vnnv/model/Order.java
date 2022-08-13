package com.android.vnnv.model;

public class Order {
    private String address;
    private String market;
    private int price;
    private String status;

    public Order() {
    }

    public Order(String address, String market, int price, String status) {
        this.address = address;
        this.market = market;
        this.price = price;
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public int getPrice() {return price;}

    public String getMarket() {return market;}

    public String getStatus() {return status;}

    public void setStatus(String status) {
        this.status = status;
    }
}
