package com.example.gymsystem_layeredproject.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PaymentDTO {
    private String paymentId;
    private String memberId;
    private String planName ;
    private String paymentMethod;
    private LocalDate date;
    private BigDecimal amount;

    public PaymentDTO(String paymentId, String memberId, String planName, String paymentMethod, LocalDate date, BigDecimal amount) {
        this.paymentId = paymentId;
        this.memberId = memberId;
        this.planName = planName;
        this.paymentMethod = paymentMethod;
        this.date = date;
        this.amount = amount;
    }

    public String getPaymentId() {return paymentId;}
    public String getMemberId() {return memberId;}
    public String getPlanName() {return planName;}
    public String getPaymentMethod() {return paymentMethod;}
    public LocalDate getDate() {return date;}
    public BigDecimal getAmount() {return amount;}

}
