package com.amaap.creditcard.repository.impl;

import com.amaap.creditcard.domain.model.entity.CreditCard;
import com.amaap.creditcard.repository.CreditCardRepository;
import com.amaap.creditcard.repository.db.InMemoryDatabase;
import com.google.inject.Inject;

import java.util.List;

public class InMemoryCreditCardRepository implements CreditCardRepository {
    private final InMemoryDatabase inMemoryDatabase;

    @Inject
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
