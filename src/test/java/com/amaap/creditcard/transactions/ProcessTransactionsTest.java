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
   private Customer customer;
   private ProcessTransactions processTransaction;
   private CreditCard creditCard;

    @BeforeEach
    void setup() throws InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {
        customer = Customer.createNewCustomer(1, "Sudhir T", "sudhir@gmail.com");
        creditCard = new CreditCard(1212, customer); // Initialize the creditCard field
        processTransaction = new ProcessTransactions(creditCard);
    }


    @Test
    void shouldBeAbleToGetAllTransactions() throws InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {

        Transaction.makeTransaction(1, LocalDate.now(), "Grocery", creditCard, 10);
        Transaction.makeTransaction(1, LocalDate.now(), "Grocery", creditCard, 10);
        List<Transaction> transactions = processTransaction.getAllTransactions();
        assertEquals(2, transactions.size());

    }

    @Test
    void shouldAbleToReturnTotalAmountSpentCurrentMonth() throws InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {

        Transaction.makeTransaction(1, LocalDate.now(), "Grocery", creditCard, 10);
        Transaction.makeTransaction(1, LocalDate.now(), "Grocery", creditCard, 10);
        Transaction.makeTransaction(1, LocalDate.now(), "Grocery", creditCard, 10);
        int amount = processTransaction.getTotalAmountSpentOnCurrentMonth();
        Assertions.assertEquals(30, amount);

    }

    @Test
    void shouldAbleToReturnTotalAmountSpentLastMonth() throws InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {

        Transaction.makeTransaction(1, LocalDate.parse("2024-02-13"), "Grocery", creditCard, 10);
        Transaction.makeTransaction(2, LocalDate.parse("2024-02-13"), "Grocery", creditCard, 20);
        Transaction.makeTransaction(1, LocalDate.now(), "Grocery", creditCard, 10);
        int amount = processTransaction.getTotalAmountSpentOnLastMonth();
        Assertions.assertEquals(30, amount);

    }

    @Test
    void shouldAbleToFilterTransactionsCurrentMonth() throws InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {

        Transaction.makeTransaction(1, LocalDate.parse("2024-02-13"), "Grocery", creditCard, 10);
        Transaction.makeTransaction(2, LocalDate.parse("2024-02-13"), "Grocery", creditCard, 20);
        Transaction.makeTransaction(1, LocalDate.now(), "Grocery", creditCard, 10);
        Transaction.makeTransaction(1, LocalDate.now(), "Grocery", creditCard, 10);
        List<Transaction> transactions = processTransaction.getCurrrentMonthTransactions();
        Assertions.assertEquals(2, transactions.size());

    }

    @Test
    void shouldAbleToFilterTransactionsByPreviousMonth() throws InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {

        Transaction.makeTransaction(1, LocalDate.parse("2024-02-13"), "Grocery", creditCard, 10);
        Transaction.makeTransaction(2, LocalDate.parse("2024-02-13"), "Grocery", creditCard, 20);
        Transaction.makeTransaction(1, LocalDate.now(), "Grocery", creditCard, 10);
        Transaction.makeTransaction(1, LocalDate.now(), "Grocery", creditCard, 10);
        List<Transaction> transactions = processTransaction.getTransactionsOfLastMonth();
        Assertions.assertEquals(2, transactions.size());

    }

    @Test
    void shouldAbleToGroupTransactionsByCategoryCurrentMonth() {
        Transaction.makeTransaction(1, LocalDate.parse("2024-02-13"), "Grocery", creditCard, 10);
        Transaction.makeTransaction(2, LocalDate.parse("2024-02-13"), "Grocery", creditCard, 20);
        Transaction.makeTransaction(1, LocalDate.now(), "Grocery", creditCard, 10);
        Transaction.makeTransaction(1, LocalDate.now(), "Grocery", creditCard, 10);
        Transaction.makeTransaction(1, LocalDate.now(), "Travel", creditCard, 10);

        Map<String, Integer> transactions = processTransaction.groupTransactionsByCategoryOfCurrentMonth();
        for (Map.Entry<String, Integer> entry : transactions.entrySet()) {
            System.out.println("Category: " + entry.getKey());
            System.out.println("Total Amount: " + entry.getValue());
            System.out.println("-------------------------");
        }
        Assertions.assertEquals(2, transactions.size());
    }

    @Test
    void shouldAbleToGroupTransactionsByCategoryOfLastMonth() {
        Transaction.makeTransaction(1, LocalDate.parse("2024-02-13"), "Grocery", creditCard, 10);
        Transaction.makeTransaction(2, LocalDate.parse("2024-02-14"), "travel", creditCard, 20);
        Transaction.makeTransaction(2, LocalDate.parse("2024-01-13"), "shopping", creditCard, 20);
        Transaction.makeTransaction(1, LocalDate.now(), "Grocery", creditCard, 10);
        Transaction.makeTransaction(1, LocalDate.now(), "Grocery", creditCard, 10);
        Transaction.makeTransaction(1, LocalDate.now(), "Travel", creditCard, 10);

        Map<String, Integer> transactions = processTransaction.groupTransactionsByCategoryOfLastMonth();
        for (Map.Entry<String, Integer> entry : transactions.entrySet()) {
            System.out.println("Category: " + entry.getKey());
            System.out.println("Total Amount: " + entry.getValue());
            System.out.println("-------------------------");
        }
        Assertions.assertEquals(2, transactions.size());
    }


}
