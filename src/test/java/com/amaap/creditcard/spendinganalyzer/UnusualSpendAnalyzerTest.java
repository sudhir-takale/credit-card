package com.amaap.creditcard.spendinganalyzer;

import com.amaap.creditcard.domain.CreditCard;
import com.amaap.creditcard.domain.Customer;
import com.amaap.creditcard.domain.exceptions.InvalidCustomerEmailException;
import com.amaap.creditcard.domain.exceptions.InvalidCustomerIdException;
import com.amaap.creditcard.domain.exceptions.InvalidCustomerNameException;
import com.amaap.creditcard.spendinganalyzer.exceptions.InvalidThresholdPercentageException;
import com.amaap.creditcard.transactions.ProcessTransactions;
import com.amaap.creditcard.transactions.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Map;

public class UnusualSpendAnalyzerTest {
    private CreditCard creditCard;
    private UnusualSpendAnalyzer unusualSpendAnalyzer;


    @BeforeEach
    void setup() throws InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {
        Customer customer = Customer.createNewCustomer(1, "Sudhir T", "sudhir@gmail.com");
        creditCard = new CreditCard(1212, customer);
        ProcessTransactions processTransaction = new ProcessTransactions(creditCard);
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


    @Test
    void shouldBeAbleToSetThreshold() throws InvalidThresholdPercentageException {

        unusualSpendAnalyzer.setThreshold(12.3);
        double result = unusualSpendAnalyzer.getThreshold();
        Assertions.assertEquals(12.3, result);

    }

    @Test
    void shouldThrowInvalidThresholdPercentIfNegativeOrZeroPercentageIsPassed() {
        Assertions.assertThrows(InvalidThresholdPercentageException.class,
                () -> unusualSpendAnalyzer.setThreshold(0));

    }
}



