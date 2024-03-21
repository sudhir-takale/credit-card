package com.amaap.creditcard.transactions;


import com.amaap.creditcard.domain.CreditCard;

import java.time.LocalDate;

public class TransactionValidator {

    public static boolean validateTransaction(int transactionId, LocalDate dateOfTransaction, String category, CreditCard creditCard, int amount) {
        return
                validateTransactionId(transactionId) &&
                        validateDateOfTransaction(dateOfTransaction) &&
                        validateCategory(category) &&
                        validateCreditCard(creditCard) &&
                        validateAmount(amount);
    }

    private static boolean validateTransactionId(int transactionId) {
        if (transactionId <= 0) {
            throw new IllegalArgumentException("Transaction ID should be greater than 0");
        }
        return true;
    }

    private static boolean validateDateOfTransaction(LocalDate dateOfTransaction) {
        if (dateOfTransaction == null || dateOfTransaction.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date of transaction should not be null and should be today or earlier");
        }
        return true;
    }

    private static boolean validateCategory(String category) {
        if (category == null || category.isEmpty()) {
            throw new IllegalArgumentException("Category should not be null or empty");
        }
        return true;
    }

    private static boolean validateCreditCard(CreditCard creditCard) {
        if (creditCard == null || creditCard.getCustomer() == null) {
            throw new NullPointerException("Credit Card or associated customer cannot be null");
        }
        return true;
    }

    private static boolean validateAmount(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount should be greater than 0");
        }
        return true;
    }
}
