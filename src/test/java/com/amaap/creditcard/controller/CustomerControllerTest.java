package com.amaap.creditcard.controller;

import com.amaap.creditcard.controller.dto.HttpsStatus;
import com.amaap.creditcard.controller.dto.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerControllerTest {

    CustomerController customerController = new CustomerController();

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


}
