package com.amaap.creditcard.repository.db;

import com.amaap.creditcard.domain.model.entity.CreditCard;
import com.amaap.creditcard.domain.model.entity.Customer;
import com.amaap.creditcard.domain.model.entity.Transaction;
import com.amaap.creditcard.domain.model.entity.exception.InvalidCustomerDataException;
import com.amaap.creditcard.domain.model.entity.exception.InvalidTransactionParameters;
import com.amaap.creditcard.domain.model.valueobject.Category;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InMemoryDatabaseTest {

    InMemoryDatabase inMemoryDatabase = new FakeInMemoryDatabase();

    @Test
    void shouldBeAbleToSaveNewCustomer() throws InvalidCustomerDataException {
        // arrange
        Customer customer = Customer.create("Sudhir", "sudhir@gmail.com");

        // act
        Customer add = inMemoryDatabase.add(customer);

        // assert
        assertEquals(customer, add);
    }

    @Test
    void shouldBeAbleToGetCustomerById() throws InvalidCustomerDataException {
        // arrange
        Customer customer = Customer.create("Sudhir", "sudhir@gmail.com");
        inMemoryDatabase.add(customer);

        // act
        Optional<Customer> actual = inMemoryDatabase.getCustomerById(1);

        // assert
        assertEquals(customer, actual.get());
    }

    @Test
    void shouldBeAbleToGetAllCustomers() throws InvalidCustomerDataException {
        // arrange
        inMemoryDatabase.add(Customer.create("Sudhir", "sudhir@gmail.com"));
        inMemoryDatabase.add(Customer.create("Sudhir", "sudhir@gmail.com"));
        inMemoryDatabase.add(Customer.create("Sudhir", "sudhir@gmail.com"));

        // act
        List<Customer> customers = inMemoryDatabase.getCustomers();

        // assert
        assertEquals(3, customers.size());
    }

    @Test
    void shouldBeAbleToSaveNewCreditCard() throws InvalidCustomerDataException {
        // arrange
        CreditCard card = new CreditCard(Customer.create("Sudhir", "sudhirtakale@gmail.com"));

        // act
        int actual = inMemoryDatabase.insert(card);

        // assert
        assertEquals(1, actual);
    }

    @Test
    void shouldBeAbleToSaveTransaction() throws InvalidTransactionParameters {
        // arrange
        Transaction transaction = Transaction.create(1, LocalDate.now(), Category.TRAVEL, 34.34);

        // act
        Transaction actual = inMemoryDatabase.save(transaction);

        // assert
        assertEquals(transaction, actual);
        assertEquals(1, actual.getId());
    }

    @Test
    void shouldBeAbleToGetAllTransactions() throws InvalidTransactionParameters {
        // arrange
        inMemoryDatabase.save(Transaction.create(1, LocalDate.now(), Category.TRAVEL, 34.34));
        inMemoryDatabase.save(Transaction.create(1, LocalDate.now(), Category.TRAVEL, 34.34));
        inMemoryDatabase.save(Transaction.create(1, LocalDate.now(), Category.TRAVEL, 34.34));

        // act
        List<Transaction> transactions = inMemoryDatabase.getTransactions();

        // assert
        assertEquals(3, transactions.size());

    }

    @Test
    void shoudlBeAbleToGetTransactionById() throws InvalidTransactionParameters {
        // arrange
        Transaction expected = inMemoryDatabase.save(Transaction.create(1, LocalDate.now(), Category.TRAVEL, 34.34));

        // act
        Optional<Transaction> actual = inMemoryDatabase.getTransactionFor(1);

        // assert
        assertEquals(expected, actual.get());
        assertEquals(1, actual.get().getId());
    }

    @Test
    void shouldBeAbleToGetAllCreditCards() throws InvalidCustomerDataException {
        // arrange
        inMemoryDatabase.insert(new CreditCard(Customer.create("Sudhir", "sudhirtakale@gmail.com")));
        inMemoryDatabase.insert(new CreditCard(Customer.create("Sudhir", "sudhirtakale@gmail.com")));
        inMemoryDatabase.insert(new CreditCard(Customer.create("Sudhir", "sudhirtakale@gmail.com")));

        // act
        List<CreditCard> creditCards = inMemoryDatabase.getCreditCards();

        // assert
        assertEquals(3, creditCards.size());


    }
}