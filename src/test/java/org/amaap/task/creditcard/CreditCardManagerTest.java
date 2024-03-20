package org.amaap.task.creditcard;

import org.amaap.task.creditcard.alerts.EmailHandler;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class CreditCardManagerTest {


    @Mock
    Customer customer;
    @Mock
    CreditCard creditCard;
    @Mock
    UnusualSpendAnalyzer unusualSpendAnalyzer;
    @Mock
    EmailHandler emailHandler;

    @InjectMocks
    CreditCardManager creditCardManager;


    @Test
    void shouldAbleToCreateNewCustomer() throws InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {

//        when(Customer.createNewCustomer(1, "Sudhir T", "sudhirtakale99@gmail.com")).thenReturn(true);
        Customer user = creditCardManager.createCustomer(1, "Sudhir T", "sudhirtakale99@gmail.com");
        Assertions.assertNotNull(user);


    }

    @Test
    void shouldBeAbleToCreateACreditCardForCustomer() throws NullCustomerException, InvalidCardNumberException {
        when(creditCard.createNewCreditCard(1212, customer)).thenReturn(true);
        boolean result = creditCardManager.createNewCreditCard(1212, customer);

        Assertions.assertTrue(result);
    }


    @Test
    void shouldReturnAllTransactions() throws  InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {
        Customer newCustomer = creditCardManager.createCustomer(1, "Sudhir T", "sudhirtakale99@gmail.com");
        CreditCard card = new CreditCard(1212, newCustomer);
        Transaction.makeTransaction(1, LocalDate.now(), "Travel", card, 12);
        Transaction.makeTransaction(2, LocalDate.now(), "Travel", card, 17);
        Transaction.makeTransaction(3, LocalDate.now(), "Travel", card, 19);
        List<Transaction> transactions = creditCardManager.getAllTransactions();
        Assertions.assertEquals(3, transactions.size());
    }

    @Test
    void shouldBeAbleToCheckWhetherThereIsAnyUnusualSpendOrNot()  {

        when(unusualSpendAnalyzer.isThereIsUnusualSpendingThisMonth()).thenReturn(true);
        boolean result = creditCardManager.checkForUnusualSpend();

        Assertions.assertTrue(result);
    }

    @Test
    void shouldAbleToFilterCategoriesWhichHasUnusualSpending()  {
        when(unusualSpendAnalyzer.isThereIsUnusualSpendingThisMonth()).thenReturn(true);
        when(unusualSpendAnalyzer.categoriesInWhichSpendingIsUnusual()).thenReturn(new HashMap<>());

        Map<String, Integer> result = creditCardManager.categoriesInWhichSpendingIsUnusual();

        Assertions.assertEquals(new HashMap<>(), result);

        verify(unusualSpendAnalyzer, times(1)).categoriesInWhichSpendingIsUnusual();
    }

    @Test
    void shouldAbleToSendEmailMessageToCustomer() throws InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {

        when(unusualSpendAnalyzer.isThereIsUnusualSpendingThisMonth()).thenReturn(true);
        when(creditCardManager.categoriesInWhichSpendingIsUnusual()).thenReturn(new HashMap<>());

        Customer customer1 = Customer.createNewCustomer(1, "sudhir t", "Sudhir@gmail.com");

        verify(emailHandler, never()).sendEmailToCustomer(new HashMap<>(), customer1);

        creditCardManager.sendMessageToCustomer(customer1);

    }
}
