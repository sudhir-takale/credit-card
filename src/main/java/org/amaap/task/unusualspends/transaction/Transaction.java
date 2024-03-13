package org.amaap.task.unusualspends.transaction;

import org.amaap.task.unusualspends.domain.CreditCard;

import java.time.LocalDate;
import java.util.Objects;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return getTransactionId() == that.getTransactionId() &&
                Objects.equals(getDateOfTransaction(), that.getDateOfTransaction()) &&
                Objects.equals(getCategory(), that.getCategory()) &&
                Objects.equals(getCreditCard(), that.getCreditCard()) &&
                getAmount() == that.getAmount();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTransactionId(), getDateOfTransaction(), getCategory(), getCreditCard(), getAmount());
    }


}
