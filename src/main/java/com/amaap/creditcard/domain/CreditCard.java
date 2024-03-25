package com.amaap.creditcard.domain;


import com.amaap.creditcard.domain.exceptions.InvalidCardNumberException;
import com.amaap.creditcard.domain.exceptions.NullCustomerException;
import com.amaap.creditcard.transactions.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CreditCard {

    private List<Transaction> transactions;
    private int cardNumber;
    private Customer customer;


    public CreditCard(int cardNumber, Customer customer) {
        this.cardNumber = cardNumber;
        this.customer = customer;
        transactions = new ArrayList<>();
    }

    public CreditCard() {

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

    public List<Transaction> getTransactions() {
        return transactions;
    }


}
