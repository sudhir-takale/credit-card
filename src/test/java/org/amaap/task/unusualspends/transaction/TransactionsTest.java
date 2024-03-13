package org.amaap.task.unusualspends.transaction;

import org.amaap.task.unusualspends.domain.CreditCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

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
    void shouldReturnTotalAmountSpentInLastMonth() {

        transactions.makeTransaction(new Transaction(1, LocalDate.parse("2024-02-12"), "Travel", card, 10));
        transactions.makeTransaction(new Transaction(2, LocalDate.parse("2024-02-12"), "Travel", card, 20));
        transactions.makeTransaction(new Transaction(3, LocalDate.parse("2024-02-12"), "Grocery", card, 20));
        transactions.makeTransaction(new Transaction(4, LocalDate.parse("2024-03-12"), "Grocery", card, 10));


        Assertions.assertEquals(50, transactions.getTotalAmountSpentOnLastMonth());


    }



    @Test
    void shouldAbleToGroupTransactionsByCategories() {
        // Arrange
        CreditCard card = new CreditCard(1212, "Sudhir t");
        Transactions transactions = new Transactions();

        transactions.makeTransaction(new Transaction(1, LocalDate.parse("2024-02-12"), "Travel", card, 10));
        transactions.makeTransaction(new Transaction(2, LocalDate.parse("2024-02-12"), "Travel", card, 20));
        transactions.makeTransaction(new Transaction(3, LocalDate.parse("2024-03-12"), "Grocery", card, 20));

        Map<String, List<Transaction>> groupedTransactions = transactions.groupTransactionsByCategory();

        List<Transaction> travelTransactions = groupedTransactions.get("Travel");

        List<Transaction> expectedTravelTransactions = Arrays.asList(
                new Transaction(1, LocalDate.parse("2024-02-12"), "Travel", card, 10),
                new Transaction(2, LocalDate.parse("2024-02-12"), "Travel", card, 20)
        );


        Assertions.assertIterableEquals(expectedTravelTransactions, travelTransactions);
    }


    @Test
    void shouldAbleToGroupTransactionsByCategoryOfPreviousMonth() {
        // Arrange
        CreditCard card = new CreditCard(1212, "Sudhir t");
        Transactions transactions = new Transactions(); // Assuming Transactions is the class containing the groupTransactionsByCategoryOfPreviousMonth method

        transactions.makeTransaction(new Transaction(1, LocalDate.parse("2024-02-12"), "Travel", card, 10));
        transactions.makeTransaction(new Transaction(2, LocalDate.parse("2024-02-12"), "Travel", card, 20));
        transactions.makeTransaction(new Transaction(3, LocalDate.parse("2024-03-12"), "Grocery", card, 20));
        transactions.makeTransaction(new Transaction(4, LocalDate.parse("2024-03-12"), "Grocery", card, 10));

        // Act
        Map<String, List<Transaction>> groupedTransactions = transactions.groupTransactionsByCategoryOfPreviousMonth();

        // Assert
        List<Transaction> travelTransactions = groupedTransactions.get("Travel");

        // Expecting transactions from the previous month (February in this case)
        List<Transaction> expectedTravelTransactions = Arrays.asList(
                new Transaction(1, LocalDate.parse("2024-02-12"), "Travel", card, 10),
                new Transaction(2, LocalDate.parse("2024-02-12"), "Travel", card, 20)
        );



        Assertions.assertIterableEquals(expectedTravelTransactions, travelTransactions);
    }

    @Test
    void shouldGroupTransactionsByCategoryInPreviousMonth() {
        // Arrange
        Transactions transactions = new Transactions();
        CreditCard card = new CreditCard(1212, "Sudhir t");

        // Add transactions for the previous month
        transactions.makeTransaction(new Transaction(1, LocalDate.parse("2024-02-12"), "Travel", card, 10));
        transactions.makeTransaction(new Transaction(2, LocalDate.parse("2024-02-15"), "Grocery", card, 15));
        transactions.makeTransaction(new Transaction(3, LocalDate.parse("2024-03-12"), "Grocery", card, 20));
        transactions.makeTransaction(new Transaction(4, LocalDate.parse("2024-03-15"), "Travel", card, 25));

        // Act
        Map<String, List<Transaction>> groupedTransactions = transactions.groupTransactionsByCategoryOfPreviousMonth();

        // Assert
        assertEquals(2, groupedTransactions.size());

        List<Transaction> travelTransactions = groupedTransactions.get("Travel");
        List<Transaction> groceryTransactions = groupedTransactions.get("Grocery");

        assertEquals(1, travelTransactions.size());
        assertEquals(1, groceryTransactions.size());


        assertEquals(10, travelTransactions.get(0).getAmount());
        assertEquals(15, groceryTransactions.get(0).getAmount());
    }

}
