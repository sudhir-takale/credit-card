package org.amaap.task.creditcard;

import org.amaap.task.creditcard.domain.CreditCard;
import org.amaap.task.creditcard.domain.Customer;
import org.amaap.task.creditcard.domain.exceptions.*;
import org.amaap.task.creditcard.spendinganalyzer.UnusualSpendAnalyzer;
import org.amaap.task.creditcard.transactions.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CreditCardManagerTest {


    @Mock
    Customer customer;
    @Mock
    CreditCard creditCard;
    @Mock
    UnusualSpendAnalyzer unusualSpendAnalyzer;

    @InjectMocks
    CreditCardManager creditCardManager;


    @Test
    void shouldAbleToCreateNewCustomer() throws InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {

//        when(Customer.createNewCustomer(1, "Sudhir T", "sudhirtakale99@gmail.com")).thenReturn(true);
        Customer user = creditCardManager.createCustomer(1, "Sudhir T", "sudhirtakale99@gmail.com");
        Assertions.assertNotNull(user);


    }

    @Test
    void shouldBeAbleToCreateACreditCardForCustomer() throws InvalidCardNumberException, NullCustomerException, InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {
        Customer newCustomer = creditCardManager.createCustomer(1, "Sudhir T", "sudhirtakale99@gmail.com");
        when(creditCard.createNewCreditCard(1212, customer)).thenReturn(true);
        boolean result = creditCardManager.createNewCreditCard(1212, customer);

        Assertions.assertTrue(result);
    }


    @Test
    void shouldReturnAllTransactions() throws Exception, InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException, InvalidCardNumberException, NullCustomerException {
        Customer newCustomer = creditCardManager.createCustomer(1, "Sudhir T", "sudhirtakale99@gmail.com");
        CreditCard card = new CreditCard(1212, newCustomer);
        Transaction.makeTransaction(1, LocalDate.now(), "Travel", card, 12);
        Transaction.makeTransaction(2, LocalDate.now(), "Travel", card, 17);
        Transaction.makeTransaction(3, LocalDate.now(), "Travel", card, 19);
        List<Transaction> transactions = creditCardManager.getAllTransactions();
        Assertions.assertEquals(3, transactions.size());
    }

    @Test
    void shouldBeAbleToCheckWhetherThereIsAnyUnusualSpendOrNot() throws InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {
        Customer newCustomer = creditCardManager.createCustomer(1, "Sudhir T", "sudhirtakale99@gmail.com");
        CreditCard card = new CreditCard(1212, newCustomer);

        when(unusualSpendAnalyzer.isThereIsUnusualSpendingThisMonth()).thenReturn(true);
        boolean result = creditCardManager.checkForUnusualSpend();

        Assertions.assertTrue(result);
    }






}
