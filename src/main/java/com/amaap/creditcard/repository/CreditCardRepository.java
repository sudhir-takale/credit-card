package com.amaap.creditcard.repository;

import com.amaap.creditcard.domain.model.entity.CreditCard;

public interface CreditCardRepository {
    int save(CreditCard card);
}