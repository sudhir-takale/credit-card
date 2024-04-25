package com.amaap.creditcard.domain.model.entity.validator;

import com.amaap.creditcard.domain.model.valueobject.Category;

import java.time.LocalDate;

public class TransactionValidator {
    public static boolean validate(int cardId, LocalDate date, Category category, double amount) {
        return (cardId >= 0 && category != null && amount >= 0);
    }
}
