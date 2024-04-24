package com.amaap.creditcard.domain.model.entity;

import com.amaap.creditcard.domain.model.entity.exception.InvalidTransactionParameters;
import com.amaap.creditcard.domain.model.entity.validator.TransactionValidator;
import com.amaap.creditcard.domain.model.valueobject.Category;

import java.time.LocalDate;
import java.util.Objects;

public class Transaction {

    private int id;
    private int cardId;
    private LocalDate date;
    private Category category;
    private Double amount;

    private Transaction(Double amount, int cardId, Category category, LocalDate date) {
        this.amount = amount;
        this.cardId = cardId;
        this.category = category;
        this.date = date;
    }

    public static Transaction create(int cardId, LocalDate date, Category category, double amount) throws InvalidTransactionParameters {

        if (TransactionValidator.validate(cardId, date, category, amount)) {
            return new Transaction(amount, cardId, category, date);
        } else {
            throw new InvalidTransactionParameters("Invalid transaction details");
        }

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return cardId == that.cardId && Objects.equals(date, that.date) && category == that.category && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId, date, category, amount);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public int getCardId() {
        return cardId;
    }
}
