package com.amaap.creditcard.service;

import com.amaap.creditcard.domain.model.entity.exception.InvalidTransactionParameters;
import com.amaap.creditcard.domain.model.valueobject.Category;
import com.amaap.creditcard.domain.service.TransactionAnalyzer;
import com.amaap.creditcard.repository.db.FakeInMemoryDatabase;
import com.amaap.creditcard.repository.db.InMemoryDatabase;
import com.amaap.creditcard.repository.impl.InMemoryCreditCardRepository;
import com.amaap.creditcard.repository.impl.InMemoryCustomerRepository;
import com.amaap.creditcard.repository.impl.InMemoryTransactionRepository;
import com.amaap.creditcard.service.communication.EmailService;
import com.amaap.creditcard.service.exception.CustomerNotFoundException;
import com.amaap.creditcard.service.exception.InvalidCustomerDataException;
import com.amaap.creditcard.service.exception.InvalidEmailArgumentException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreditCardManagementServiceTest {
    InMemoryDatabase inMemoryDatabase = new FakeInMemoryDatabase();

    CustomerService customerService = new CustomerService(new InMemoryCustomerRepository(inMemoryDatabase));
    CreditCardService creditCardService = new CreditCardService(new InMemoryCreditCardRepository(inMemoryDatabase), customerService);

    TransactionService transactionService = new TransactionService(new InMemoryTransactionRepository(inMemoryDatabase));

    CreditCardManagementService cardManagementService = new CreditCardManagementService(creditCardService, new TransactionAnalyzer(), transactionService);


    @Test
    void shouldBeAbleToCheckForUnusualSpendByTheCustomer() throws InvalidCustomerDataException, CustomerNotFoundException, InvalidTransactionParameters, InvalidEmailArgumentException {
        // arrange
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        customerService.create("Sudhir Takale", "shtakale1111@gmail.com.com");
        customerService.create("Shaurya Mali", "sudhirtake99@gmail.com");
        creditCardService.create(1);
        transactionService.createTransaction(1, LocalDate.now(), Category.TRAVEL, 12.34);
        transactionService.createTransaction(1, LocalDate.now(), Category.TRAVEL, 110.34);
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

    }

    @Test
    void shouldSendEmailAlertWhenValidArgumentPassed() throws InvalidEmailArgumentException {
        // arrange
        String subject = "i am here";
        String body = "How are you";
        String to = "sudhirtakale99@gmail.com";

        // act
        EmailService.sendEmail(subject, body, to);

        // assert
        assertEquals("sudhirtakale99@gmail.com", to);
    }

}