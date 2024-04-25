package com.amaap.creditcard.service;

import com.amaap.creditcard.domain.model.entity.Transaction;
import com.amaap.creditcard.domain.model.entity.exception.InvalidTransactionParameters;
import com.amaap.creditcard.domain.model.valueobject.Category;
import com.amaap.creditcard.domain.service.SpendProcessor;
import com.amaap.creditcard.repository.db.FakeInMemoryDatabase;
import com.amaap.creditcard.repository.db.InMemoryDatabase;
import com.amaap.creditcard.repository.impl.InMemoryCreditCardRepository;
import com.amaap.creditcard.repository.impl.InMemoryCustomerRepository;
import com.amaap.creditcard.repository.impl.InMemoryTransactionRepository;
import com.amaap.creditcard.service.exception.CustomerNotFoundException;
import com.amaap.creditcard.service.exception.InvalidCustomerDataException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreditCardManagementServiceTest {
    InMemoryDatabase inMemoryDatabase = new FakeInMemoryDatabase();

    CustomerService customerService = new CustomerService(new InMemoryCustomerRepository(inMemoryDatabase));
    CreditCardService creditCardService = new CreditCardService(new InMemoryCreditCardRepository(inMemoryDatabase), customerService);

    TransactionService transactionService = new TransactionService(new InMemoryTransactionRepository(inMemoryDatabase));

    CreditCardManagementService cardManagementService = new CreditCardManagementService(creditCardService, new SpendProcessor(), transactionService);


    @Test
    void shouldBeAbleToCheckForUnusualSpendByTheCustomer() throws InvalidCustomerDataException, CustomerNotFoundException, InvalidTransactionParameters {
        // arrange
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        customerService.create("Sudhir Takale", "shtakale1111@gmail.com.com");
        customerService.create("Shaurya Mali", "sudhirtake99@gmail.com");
        creditCardService.create(1);
        Transaction transaction = transactionService.createTransaction(1, LocalDate.now(), Category.TRAVEL, 12.34);
        transactionService.createTransaction(1, LocalDate.now(), Category.TRAVEL, 110.34);
        transactionService.createTransaction(1, LocalDate.now(), Category.TRAVEL, 110.34);
        transactionService.createTransaction(1, LocalDate.now(), Category.TRAVEL, 120.34);
        transactionService.createTransaction(1, LocalDate.now(), Category.TRAVEL, 160.34);
        transactionService.createTransaction(1, LocalDate.now(), Category.SHOPPING, 10.34);
        transactionService.createTransaction(1, LocalDate.now(), Category.MEDICINE, 10.34);
        transactionService.createTransaction(1, LocalDate.now(), Category.RENT, 14.5);

        transactionService.createTransaction(1, LocalDate.parse("19-03-2024", formatter), Category.TRAVEL, 5.5);
        transactionService.createTransaction(1, LocalDate.parse("10-03-2024", formatter), Category.TRAVEL, 45.5);
        transactionService.createTransaction(1, LocalDate.parse("16-03-2024", formatter), Category.TRAVEL, 15.5);
        transactionService.createTransaction(1, LocalDate.parse("16-03-2024", formatter), Category.SHOPPING, 4.5);
        transactionService.createTransaction(1, LocalDate.parse("16-03-2024", formatter), Category.RENT, 14.5);

        // act
        boolean result = cardManagementService.checkForUnusualSpend();

        // assert
        assertTrue(result);
        assertEquals(1, transaction.getCardId());

    }







}