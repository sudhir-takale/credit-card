package org.amaap.task.unusualspends.transaction;

import org.amaap.task.unusualspends.domain.CreditCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class TransactionsTest {

    Transactions transactions;
    CreditCard card;

    @BeforeEach
    void setup() throws Exception {
        transactions = new Transactions();
        card = new CreditCard(121212, "Sudhir Takale");


    }

    @Test
    void shouldAbleToMakeTransactionAndAddToTransactionList() {


        LocalDate transactionDate = LocalDate.parse("2022-12-12");
        boolean result = transactions.makeTransaction(new Transaction(1, transactionDate, "Travel", card, 120));
        Assertions.assertTrue(result);

    }

    @Test
    void shouldAbleToProvideTotalAmountSpent() {
        LocalDate transactionDate = LocalDate.parse("2022-12-12");
        transactions.makeTransaction(new Transaction(1, transactionDate, "Travel", card, 14));
        transactions.makeTransaction(new Transaction(2, transactionDate, "Travel", card, 10));
        transactions.makeTransaction(new Transaction(3, transactionDate, "Travel", card, 10));

        Assertions.assertEquals(34, transactions.getTotalAmountSpent());


    }

    @Test
    void shouldReturnListOfTransactionsGroupedByCategory() {

    }

    @Test
    void shouldReturnTotalAmountSpentInLastMonth() {

        transactions.makeTransaction(new Transaction(1, LocalDate.parse("2024-02-12"), "Travel", card, 10));
        transactions.makeTransaction(new Transaction(2, LocalDate.parse("2024-02-12"), "Travel", card, 20));
        transactions.makeTransaction(new Transaction(3, LocalDate.parse("2024-02-12"), "Grocery", card, 20));
        transactions.makeTransaction(new Transaction(4, LocalDate.parse("2024-03-12"), "Grocery", card, 10));
//        List<Transaction> transactionsOfLastMonth = Arrays.asList(
//                new Transaction(1, LocalDate.parse("2024-02-12"), "Travel", card, 14),
//                new Transaction(2, LocalDate.parse("2024-02-12"), "Travel", card, 10),
//                new Transaction(3, LocalDate.parse("2024-02-12"), "Grocery", card, 20)
//        );

        Assertions.assertEquals(50, transactions.getTotalAmountSpentOnLastMonth());


    }


}
