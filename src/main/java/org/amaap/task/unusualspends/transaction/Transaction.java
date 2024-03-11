package org.amaap.task.unusualspends.transaction;

import org.amaap.task.unusualspends.domain.CreditCard;

import java.time.LocalDate;

public class Transaction {

    int transactionId;
    LocalDate dateOfTransaction;
    private String category;
    private CreditCard creditCard;
    private int amount;

    public Transaction(int transactionId, LocalDate dateOfTransaction, String category, CreditCard creditCard, int amount) {
        this.transactionId = transactionId;
        this.dateOfTransaction = dateOfTransaction;
        this.category = category;
        this.creditCard = creditCard;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
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

    public CreditCard getCreditCard() {
        return creditCard;
    }
}
