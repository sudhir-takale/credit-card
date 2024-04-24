package com.amaap.creditcard.repository.impl;

import com.amaap.creditcard.domain.model.entity.CreditCard;
import com.amaap.creditcard.repository.CreditCardRepository;
import com.amaap.creditcard.repository.db.InMemoryDatabase;

import java.util.List;

public class InMemoryCreditCardRepository implements CreditCardRepository {
    private final InMemoryDatabase inMemoryDatabase;

    public InMemoryCreditCardRepository(InMemoryDatabase inMemoryDatabase) {
        this.inMemoryDatabase = inMemoryDatabase;
    }

    @Override
    public int save(CreditCard card) {
        return inMemoryDatabase.insert(card);
    }

    @Override
    public List<CreditCard> getCreditCards() {
        return inMemoryDatabase.getCreditCards();
    }
}
