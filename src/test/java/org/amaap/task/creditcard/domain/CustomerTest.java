package org.amaap.task.creditcard.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerTest {


    @Test
    void shouldBeAbleToCreateCustomer() {

        boolean result = Customer.createNewCustomer(1, "Sudhir T", "Sudhirtakale99@gmail.com");
        Assertions.assertTrue(result);
    }






}
