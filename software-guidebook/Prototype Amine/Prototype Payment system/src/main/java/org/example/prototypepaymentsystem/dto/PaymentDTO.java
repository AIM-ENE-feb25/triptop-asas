package org.example.prototypepaymentsystem.dto;

public class PaymentDTO {
    private String currencyCode;
    private double amount;


    public PaymentDTO(String currencyCode, double amount) {
        this.currencyCode = currencyCode;
        this.amount = amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public double getAmount() {
        return amount;
    }
}
