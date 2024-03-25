package com.amaap.creditcard.transactions;

import com.amaap.creditcard.domain.CreditCard;
import com.amaap.creditcard.domain.Customer;
import com.amaap.creditcard.domain.exceptions.InvalidCustomerEmailException;
import com.amaap.creditcard.domain.exceptions.InvalidCustomerIdException;
import com.amaap.creditcard.domain.exceptions.InvalidCustomerNameException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProcessTransactionsTest {
    private ProcessTransactions processTransaction;
    private CreditCard creditCard;

    @BeforeEach
    void setup() throws InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {
        Customer customer = Customer.createNewCustomer(1, "Sudhir T", "sudhir@gmail.com");
        creditCard = new CreditCard(1212, customer);
        processTransaction = new ProcessTransactions(creditCard);
    }

    @Test
    void shouldBeAbleToGetAllTransactions() {
//        Arrange
        Transaction.makeTransaction(1, LocalDate.now(), "Grocery", creditCard, 10);
        Transaction.makeTransaction(1, LocalDate.now(), "Grocery", creditCard, 10);
//        Act
        List<Transaction> transactions = processTransaction.getAllTransactions();
//        Assert
        assertEquals(2, transactions.size());

    }

    @Test
    void shouldAbleToReturnTotalAmountSpentCurrentMonth() {
//        Arrange
        Transaction.makeTransaction(1, LocalDate.now(), "Grocery", creditCard, 10);
        Transaction.makeTransaction(1, LocalDate.now(), "Grocery", creditCard, 10);
        Transaction.makeTransaction(1, LocalDate.now(), "Grocery", creditCard, 10);
//        Act
        int amount = processTransaction.getTotalAmountSpentOnCurrentMonth();
//        Assert
        Assertions.assertEquals(30, amount);

    }

    @Test
    void shouldAbleToReturnTotalAmountSpentLastMonth() {
        //Arrange
        Transaction.makeTransaction(1, LocalDate.parse("2024-02-13"), "Grocery", creditCard, 10);
        Transaction.makeTransaction(2, LocalDate.parse("2024-02-13"), "Grocery", creditCard, 20);
        Transaction.makeTransaction(1, LocalDate.now(), "Grocery", creditCard, 10);
//        Act
        int amount = processTransaction.getTotalAmountSpentOnLastMonth();
//        Assert
        Assertions.assertEquals(30, amount);

    }

    @Test
    void shouldAbleToFilterTransactionsCurrentMonth() {
//        Arrange
        Transaction.makeTransaction(1, LocalDate.parse("2024-02-13"), "Grocery", creditCard, 10);
        Transaction.makeTransaction(2, LocalDate.parse("2024-02-13"), "Grocery", creditCard, 20);
        Transaction.makeTransaction(1, LocalDate.now(), "Grocery", creditCard, 10);
        Transaction.makeTransaction(1, LocalDate.now(), "Grocery", creditCard, 10);
//        Act
        List<Transaction> transactions = processTransaction.getCurrentMonthTransactions();
//        Assert
        Assertions.assertEquals(2, transactions.size());

    }

    @Test
    void shouldAbleToFilterTransactionsByPreviousMonth() {
//      Arrange
        Transaction.makeTransaction(1, LocalDate.parse("2024-02-13"), "Grocery", creditCard, 10);
        Transaction.makeTransaction(2, LocalDate.parse("2024-02-13"), "Grocery", creditCard, 20);
        Transaction.makeTransaction(1, LocalDate.now(), "Grocery", creditCard, 10);
        Transaction.makeTransaction(1, LocalDate.now(), "Grocery", creditCard, 10);
//        Act
        List<Transaction> transactions = processTransaction.getTransactionsOfLastMonth();
//        Assert
        Assertions.assertEquals(2, transactions.size());

    }

    @Test
    void shouldAbleToGroupTransactionsByCategoryCurrentMonth() {
//        Arrange
        Transaction.makeTransaction(1, LocalDate.parse("2024-02-13"), "Grocery", creditCard, 10);
        Transaction.makeTransaction(2, LocalDate.parse("2024-02-13"), "Grocery", creditCard, 20);
        Transaction.makeTransaction(1, LocalDate.now(), "Grocery", creditCard, 10);
        Transaction.makeTransaction(1, LocalDate.now(), "Grocery", creditCard, 10);
        Transaction.makeTransaction(1, LocalDate.now(), "Travel", creditCard, 10);
//        Act
        Map<String, Integer> transactions = processTransaction.groupTransactionsByCategoryOfCurrentMonth();
//        Assert
        Assertions.assertEquals(2, transactions.size());
    }

    @Test
    void shouldAbleToGroupTransactionsByCategoryOfLastMonth() {
//        Arrange
        Transaction.makeTransaction(1, LocalDate.parse("2024-02-13"), "Grocery", creditCard, 10);
        Transaction.makeTransaction(2, LocalDate.parse("2024-02-14"), "travel", creditCard, 20);
        Transaction.makeTransaction(2, LocalDate.parse("2024-01-13"), "shopping", creditCard, 20);
        Transaction.makeTransaction(1, LocalDate.now(), "Grocery", creditCard, 10);
        Transaction.makeTransaction(1, LocalDate.now(), "Grocery", creditCard, 10);
        Transaction.makeTransaction(1, LocalDate.now(), "Travel", creditCard, 10);
//        Act
        Map<String, Integer> transactions = processTransaction.groupTransactionsByCategoryOfLastMonth();
//       Assert
        Assertions.assertEquals(2, transactions.size());
    }


}
