package com.amaap.creditcard.util;

import com.amaap.creditcard.domain.model.entity.Transaction;
import com.amaap.creditcard.domain.model.entity.exception.InvalidTransactionParameters;
import com.amaap.creditcard.domain.model.valueobject.Category;

import java.time.LocalDate;

public class TransactionBuilder {

    public static Transaction createTransaction() throws InvalidTransactionParameters {
        return Transaction.create(1, LocalDate.now(), Category.TRAVEL, 34.3);
    }




}
