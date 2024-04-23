package com.amaap.creditcard.repository;

import com.amaap.creditcard.domain.model.entity.CreditCard;

public interface CreditCardRepository {
    void save(CreditCard card);
}