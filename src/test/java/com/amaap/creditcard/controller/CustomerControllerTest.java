package com.amaap.creditcard.controller;

import com.amaap.creditcard.controller.dto.HttpsStatus;
import com.amaap.creditcard.controller.dto.Response;
import com.amaap.creditcard.repository.impl.InMemoryCustomerRepository;
import com.amaap.creditcard.service.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerControllerTest {

    CustomerController customerController = new CustomerController(new CustomerService(new InMemoryCustomerRepository()));

    @Test
    void shouldBeAbleToCreateNewCustomer() {
        // arrange
        String name = "sudhir takale";
        String emailAddress = "dummyemail@gmail.com";

        // act
        Response expected = new Response(HttpsStatus.OK, "New customer created successfully");
        Response actual = customerController.create(name, emailAddress);

        // assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldReturnBadResponseIfInvalidEmailAddress() {
        // arrange
        String name = "sudhir takale";
        String emailAddress = "dummyemail@gmail2323.com23";

        // act
        Response expected = new Response(HttpsStatus.BADREQUEST, "Failed to create Customer");
        Response actual = customerController.create(name, emailAddress);

        // assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldReturnBadResponseIfInvalidNameIsPassed() {
        // arrange
        String name = "sudhir takale23";
        String emailAddress = "dummyemail@gmail.com";

        // act
        Response expected = new Response(HttpsStatus.BADREQUEST, "Failed to create Customer");
        Response actual = customerController.create(name, emailAddress);

        // assert
        Assertions.assertEquals(expected, actual);
    }


}
