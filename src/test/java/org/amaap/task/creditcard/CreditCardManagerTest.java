package org.amaap.task.creditcard;

import org.amaap.task.creditcard.domain.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class CreditCardManagerTest {


    @Mock
    Customer customer;

    @InjectMocks
    CreditCardManager creditCardManager;

    @Test
    void shouldAbleToCreateNewCustomer() {

//        when(Customer.createNewCustomer(1, "Sudhir T", "sudhirtakale99@gmail.com")).thenReturn(true);
        boolean result = creditCardManager.createCustomer(1, "Sudhir T", "sudhirtakale99@gmail.com");
        Assertions.assertTrue(result);


    }









}
