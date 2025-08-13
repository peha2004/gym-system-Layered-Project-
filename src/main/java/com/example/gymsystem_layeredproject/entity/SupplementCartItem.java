package com.example.gymsystem_layeredproject.entity;

public class SupplementCartItem {

    private String memberId;
    private String supplementId;
    private String supplementName;
    private int quantity;
    private double price;
    private double total;

    public SupplementCartItem(String memberId,String supplementId, String supplementName, int quantity, double price) {
        this.memberId = memberId;
        this.supplementId = supplementId;
        this.supplementName = supplementName;
        this.quantity = quantity;
        this.price = price;
        this.total = quantity * price;
    }
    public String getMemberId() {return memberId;}
    public String getSupplementId() {return supplementId;}
    public String getSupplementName() {return supplementName;}
    public int getQuantity() {return quantity;}
    public double getPrice() {return price;}
    public double getTotal() {return total;}
}
