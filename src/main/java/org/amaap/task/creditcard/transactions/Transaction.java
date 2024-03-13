package org.amaap.task.creditcard.transactions;

import org.amaap.task.creditcard.domain.CreditCard;
import org.amaap.task.creditcard.domain.Customer;

import java.time.LocalDate;

public class Transaction {

    private int transactionId;
    private LocalDate dateOfTransaction;
    private String category;
    private CreditCard creditCard;
    private int amount;

    public Transaction(int transactionId, LocalDate dateOfTransaction, String category, CreditCard creditCard, int amount) {

        TransactionValidator.validateTransaction(transactionId, dateOfTransaction, category,
                creditCard, amount);

        this.transactionId = transactionId;
        this.dateOfTransaction = dateOfTransaction;
        this.category = category;
        this.creditCard = creditCard;
        this.amount = amount;
    }

    public static Transaction makeTransaction(int transactionId, LocalDate dateOfTransaction, String category, CreditCard creditCard, int amount) {


        Transaction transaction = new Transaction(transactionId, dateOfTransaction, category, creditCard, amount);
        Customer.transactions.add(transaction);
        return transaction;
    }

    ;

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

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", dateOfTransaction=" + dateOfTransaction +
                ", category='" + category + '\'' +
                ", creditCard=" + creditCard +
                ", amount=" + amount +
                '}';
    }
}

