package com.example.gymsystem_layeredproject.dto;

import java.time.LocalDate;

public class SupplementTransactionHistoryDTO {
    private String transactionId;
    private String memberId;
    private LocalDate date;
    private double totalAmount;

    public SupplementTransactionHistoryDTO(String transactionId, String memberId, LocalDate date, double totalAmount) {
        this.transactionId = transactionId;
        this.memberId = memberId;
        this.date = date;
        this.totalAmount = totalAmount;
    }
    public String getTransactionId() {return transactionId;}
    public String getMemberId() {return memberId;}
    public LocalDate getDate() {return date;}
    public double getTotalAmount() {return totalAmount;}

}
