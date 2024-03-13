package org.amaap.task.creditcard.spendinganalyzer;

import org.amaap.task.creditcard.domain.CreditCard;
import org.amaap.task.creditcard.domain.Customer;
import org.amaap.task.creditcard.domain.exceptions.InvalidCustomerEmailException;
import org.amaap.task.creditcard.domain.exceptions.InvalidCustomerIdException;
import org.amaap.task.creditcard.domain.exceptions.InvalidCustomerNameException;
import org.amaap.task.creditcard.transactions.ProcessTransactions;
import org.amaap.task.creditcard.transactions.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Map;

public class UnusualSpendAnalyzerTest {
    Customer customer;
    ProcessTransactions processTransaction;
    CreditCard creditCard;
    UnusualSpendAnalyzer unusualSpendAnalyzer;


    @BeforeEach
    void setup() throws InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {
        customer = Customer.createNewCustomer(1, "Sudhir T", "sudhir@gmail.com");
        processTransaction = new ProcessTransactions(new Customer());
        creditCard = new CreditCard(1212, customer);
        unusualSpendAnalyzer = new UnusualSpendAnalyzer(processTransaction);

    }

    @Test
    void shouldAbleToCheckWhetherThereIsAnyUnusualSpending() {
        Transaction.makeTransaction(1, LocalDate.parse("2024-02-13"), "Grocery", creditCard, 10);
        Transaction.makeTransaction(2, LocalDate.parse("2024-02-13"), "travel", creditCard, 20);
        Transaction.makeTransaction(2, LocalDate.parse("2024-01-13"), "shopping", creditCard, 20);
        Transaction.makeTransaction(1, LocalDate.now(), "Grocery", creditCard, 10);
        Transaction.makeTransaction(1, LocalDate.now(), "Grocery", creditCard, 100);
        Transaction.makeTransaction(1, LocalDate.now(), "Travel", creditCard, 10);

        boolean result = unusualSpendAnalyzer.isThereIsUnusualSpendingThisMonth();
        Assertions.assertTrue(result);

    }

    @Test
    void shouldProvideCategoriesInWhichSpendingIsUnusual() {
        Transaction.makeTransaction(1, LocalDate.parse("2024-02-10"), "Grocery", creditCard, 90);
        Transaction.makeTransaction(2, LocalDate.parse("2024-02-15"), "travel", creditCard, 70);
        Transaction.makeTransaction(1, LocalDate.now(), "shopping", creditCard, 10);
        Transaction.makeTransaction(1, LocalDate.now(), "Grocery", creditCard, 50);
        Transaction.makeTransaction(1, LocalDate.now(), "Travel", creditCard, 10);
        Map<String, Integer> result = unusualSpendAnalyzer.categoriesInWhichSpendingIsUnusual();
        Assertions.assertEquals(0, result.size());
    }

}



