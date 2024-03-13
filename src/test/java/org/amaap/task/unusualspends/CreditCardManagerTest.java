
package org.amaap.task.unusualspends;

import org.amaap.task.unusualspends.domain.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class CreditCardManagerTest {

    @Test
    void shouldAbleToCreateCustomer() throws Exception {

        int customerId = 1;
        String customerName = "Sudhir T";
        String emailAddress = "sudhirtakale@demo.com";

        Customer expected = Customer.createInstance(customerId, customerName, emailAddress);
        CreditCardManager cardManager = new CreditCardManager();
        // Assert
        assertEquals(expected, cardManager.createCustomer(1, "Sudhir T", "sudhirtakale@demo.com"));



    }


}

