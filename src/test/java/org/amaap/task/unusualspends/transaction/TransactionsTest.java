package org.amaap.task.unusualspends.transaction;

import org.amaap.task.unusualspends.domain.CreditCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class TransactionsTest {


    @Test
    void shouldAbleToMakeTransactionAndAddToTransactionList() {

        Transactions transactions = new Transactions();
        CreditCard card = new CreditCard(121212, "Sudhir Takale");
        LocalDate transactionDate = LocalDate.parse("2022-12-12");
        boolean result = transactions.makeTransaction(new Transaction(1, transactionDate, "Travel", card,120));
        Assertions.assertTrue(result);

    }

    @Test
    void shouldAbleToProvideTotalAmountSpent() {
        Transactions transactions = new Transactions();
        CreditCard card = new CreditCard(121212, "Sudhir Takale");
        LocalDate transactionDate = LocalDate.parse("2022-12-12");
        transactions.makeTransaction(new Transaction(1, transactionDate, "Travel", card, 14));
        transactions.makeTransaction(new Transaction(2, transactionDate, "Travel", card, 10));
        transactions.makeTransaction(new Transaction(3, transactionDate, "Travel", card, 10));

        Assertions.assertEquals(34, transactions.getTotalAmountSpent());


    }




}
