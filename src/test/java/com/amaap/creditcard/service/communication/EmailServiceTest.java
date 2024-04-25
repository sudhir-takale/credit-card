package com.amaap.creditcard.service.communication;

import com.amaap.creditcard.service.exception.InvalidEmailArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmailServiceTest {


    @Test
    void shouldBeAbleToCreateInstanceOfClass() {
        // act
        EmailService emailService = new EmailService();

        // assert
        assertNotNull(emailService);

    }

    @Test
    void shouldThrowExceptionWhenEmailIsNotValid() {
        // assert
        assertThrows(InvalidEmailArgumentException.class, () -> EmailService.sendEmail("sudhir", "i", "su"));
    }

    @Test
    void shouldThrowExceptionWhenBodyIsEmpty() {
        // assert
        assertThrows(InvalidEmailArgumentException.class, () -> EmailService.sendEmail("sudhir", "", "sudhirtak@gmai.com"));
    }

    @Test
    void shouldThrowExceptionWhenBodyContainsOnlyNumbers() {
        // assert
        assertThrows(InvalidEmailArgumentException.class, () -> EmailService.sendEmail("sudhir", "2", "sudhirtak@gmai" +
                ".com"));
    }

}