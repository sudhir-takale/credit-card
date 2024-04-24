package com.amaap.creditcard.service;

import com.amaap.creditcard.domain.model.entity.CreditCard;
import com.amaap.creditcard.domain.service.SpendProcessor;
import com.google.inject.Inject;

import java.util.List;

public class CreditCardManagementService {
    private SpendProcessor spendProcessor;
    private CreditCardService creditCard;
    private TransactionService transactionService;

    @Inject
    public CreditCardManagementService(CreditCardService creditCard, SpendProcessor spendProcessor, TransactionService transactionService) {
        this.creditCard = creditCard;
        this.spendProcessor = spendProcessor;
        this.transactionService = transactionService;
    }

    public boolean checkForUnusualSpend() {

        return true;
    }
}
