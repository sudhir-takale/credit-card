package com.amaap.creditcard.repository;

import com.amaap.creditcard.domain.model.entity.CreditCard;

import java.util.List;

public interface CreditCardRepository {
    int save(CreditCard card);

    List<CreditCard> getCreditCards();
}