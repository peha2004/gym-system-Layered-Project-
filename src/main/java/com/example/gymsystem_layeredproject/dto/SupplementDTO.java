package com.example.gymsystem_layeredproject.dto;

public class SupplementDTO {
    private String supplementId;
    private String supplemetName;
    private double price;
    private int quantity;

    public SupplementDTO(String supplementId, String supplemetName, double price, int quantity) {
        this.supplementId = supplementId;
        this.supplemetName = supplemetName;
        this.price = price;
        this.quantity = quantity;
    }
    public String getSupplementId() {return supplementId;}
    public String getSupplemetName() {return supplemetName;}
    public double getPrice() {return price;}
    public int getQuantity() {return quantity;}

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
