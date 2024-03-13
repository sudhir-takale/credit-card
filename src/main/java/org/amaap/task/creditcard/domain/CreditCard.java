package org.amaap.task.creditcard.domain;

import org.amaap.task.creditcard.domain.exceptions.InvalidCardNumberException;
import org.amaap.task.creditcard.domain.exceptions.NullCustomerException;

public class CreditCard {

    private int cardNumber;
    private Customer customer;

    public CreditCard(int cardNumber, Customer customer) {
        this.cardNumber = cardNumber;
        this.customer = customer;
    }

    public CreditCard() {

    }

    public int getCardNumber() {
        return cardNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public boolean createNewCreditCard(int cardNumber, Customer newCustomer) throws InvalidCardNumberException, NullCustomerException {
        if (cardNumber <= 0) throw new InvalidCardNumberException(cardNumber + "Card Number is Invalid !");
        if (newCustomer == null) throw new NullCustomerException(null + "Customer is null !");
        CreditCard creditCard = new CreditCard(cardNumber, newCustomer);
        return true;
    }






}
