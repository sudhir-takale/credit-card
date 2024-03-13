package org.amaap.task.creditcard;

import org.amaap.task.creditcard.domain.CreditCard;
import org.amaap.task.creditcard.domain.Customer;
import org.amaap.task.creditcard.domain.exceptions.InvalidCardNumberException;
import org.amaap.task.creditcard.domain.exceptions.NullCustomerException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CreditCardManagerTest {


    @Mock
    Customer customer;
    @Mock
    CreditCard creditCard;

    @InjectMocks
    CreditCardManager creditCardManager;

    @Test
    void shouldAbleToCreateNewCustomer() {

//        when(Customer.createNewCustomer(1, "Sudhir T", "sudhirtakale99@gmail.com")).thenReturn(true);
        Customer user = creditCardManager.createCustomer(1, "Sudhir T", "sudhirtakale99@gmail.com");
        Assertions.assertNotNull(user);


    }

    @Test
    void shouldBeAbleToCreateACreditCardForCustomer() throws InvalidCardNumberException, NullCustomerException {
//        Customer newCustomer = creditCardManager.createCustomer(1, "Sudhir T", "sudhirtakale99@gmail.com");
        when(creditCard.createNewCreditCard(1212, customer)).thenReturn(true);
        boolean result = creditCardManager.createNewCreditCard(1212, customer);

        Assertions.assertTrue(result);
    }


}
