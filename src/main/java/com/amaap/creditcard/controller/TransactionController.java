package com.amaap.creditcard.controller;

import com.amaap.creditcard.controller.dto.HttpsStatus;
import com.amaap.creditcard.controller.dto.Response;
import com.amaap.creditcard.domain.model.entity.exception.InvalidTransactionParameters;
import com.amaap.creditcard.domain.model.valueobject.Category;
import com.amaap.creditcard.service.TransactionService;

import java.time.LocalDate;

public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public Response createTransaction(int cardId, LocalDate date, Category category, double amount) {
        try{
            transactionService.createTransaction(cardId, date, category, amount);
            return new Response(HttpsStatus.OK,"Transaction created");

        }catch (InvalidTransactionParameters exception){
            return new Response(HttpsStatus.BADREQUEST, "Transaction not created");
        }
    }
}