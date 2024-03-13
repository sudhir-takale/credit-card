package org.amaap.task.creditcard.domain;

public class CreditCard {

    private int cardNumber;
    private Customer customer;

    public CreditCard(int cardNumber, Customer customer) {
        this.cardNumber = cardNumber;
        this.customer = customer;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public boolean createNewCreditCard(int cardNumber, Customer newCustomer) {
        CreditCard creditCard = new CreditCard(cardNumber, newCustomer);
        return true;
    }
}
