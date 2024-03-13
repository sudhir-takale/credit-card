package org.amaap.task.unusualspends.domain;

public class CreditCard {
    private int cardNumber;
    private String customerName;
    public CreditCard(int cardNumber, String customerName) {
        this.cardNumber = cardNumber;
        this.customerName = customerName;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

}
