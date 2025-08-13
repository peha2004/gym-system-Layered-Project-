package com.example.gymsystem_layeredproject.dto;

import java.time.LocalDate;
import java.util.List;

public class SupplementTransactionDTO {
    private String transactionId;
    private String memberId;
    private LocalDate date;
    private double totalAmount;
    private List<SupplementCartItemDTO> items;

    public SupplementTransactionDTO(String transactionId, String memberId, LocalDate date, double totalAmount, List<SupplementCartItemDTO> items) {
        this.transactionId = transactionId;
        this.memberId = memberId;
        this.date = date;
        this.totalAmount = totalAmount;
        this.items = items;
    }
public String getTransactionId() {return transactionId;}
    public String getMemberId() {return memberId;}
    public LocalDate getDate() {return date;}
    public double getTotalAmount() {return totalAmount;}
    public List<SupplementCartItemDTO> getItems() {return items;}

}