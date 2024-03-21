package com.amaap.creditcard;

import com.amaap.creditcard.alerts.EmailCreator;
import com.amaap.creditcard.alerts.EmailHandler;
import com.amaap.creditcard.domain.CreditCard;
import com.amaap.creditcard.domain.Customer;
import com.amaap.creditcard.domain.exceptions.*;
import com.amaap.creditcard.spendinganalyzer.UnusualSpendAnalyzer;
import com.amaap.creditcard.spendinganalyzer.exceptions.InvalidThresholdPercentageException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
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
    @Mock
    EmailCreator emailCreator;

    @InjectMocks
    CreditCardManager creditCardManager;


    @Test
    void shouldAbleToCreateNewCustomer() throws InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {

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
    void shouldBeAbleToCheckWhetherThereIsAnyUnusualSpendOrNot() {

        when(unusualSpendAnalyzer.isThereIsUnusualSpendingThisMonth()).thenReturn(true);
        boolean result = creditCardManager.checkForUnusualSpend();

        Assertions.assertTrue(result);
    }

    @Test
    void shouldAbleToFilterCategoriesWhichHasUnusualSpending() {
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

    @Test
    void shouldBeAbleToSetThresholdPercentageForUnusualSpend() throws InvalidThresholdPercentageException {
        when(unusualSpendAnalyzer.setThreshold(149.0)).thenReturn(149.0);
        double result = creditCardManager.setThresholdPercent(149);
        Assertions.assertEquals(149.0, result);

    }

    @Test
    void shouldBeABleToCreateACustomEmail() {
//    Arrange
        when(emailCreator.createCustomEmail()).thenReturn(new EmailCreator("Hii", "how are you", "How can I help YOu"));
//        act
        EmailCreator email = creditCardManager.createCustomEmail();
//        assert
        Assertions.assertNotNull(email);


    }


}
