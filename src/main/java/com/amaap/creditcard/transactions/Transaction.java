package com.amaap.creditcard.transactions;


import com.amaap.creditcard.domain.CreditCard;
import com.amaap.creditcard.transactions.validator.TransactionValidator;

import java.time.LocalDate;

public class Transaction {

    private int transactionId;
    private LocalDate dateOfTransaction;
    private String category;
    private CreditCard creditCard;
    private int amount;

    public Transaction(int transactionId, LocalDate dateOfTransaction, String category, CreditCard creditCard, int amount) {

        if (TransactionValidator.validateTransaction(transactionId, dateOfTransaction, category, creditCard, amount)) {

            this.transactionId = transactionId;
            this.dateOfTransaction = dateOfTransaction;
            this.category = category;
            this.creditCard = creditCard;
            this.amount = amount;
        }
    }

    public static Transaction makeTransaction(int transactionId, LocalDate dateOfTransaction, String category, CreditCard creditCard, int amount) {

        Transaction transaction = new Transaction(transactionId, dateOfTransaction, category, creditCard, amount);
        creditCard.getTransactions().add(transaction);
        return transaction;
    }


    public CreditCard getCreditCard() {
        return creditCard;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public LocalDate getDateOfTransaction() {
        return dateOfTransaction;
    }

    public String getCategory() {
        return category;
    }

    public int getAmount() {
        return amount;
    }

}

