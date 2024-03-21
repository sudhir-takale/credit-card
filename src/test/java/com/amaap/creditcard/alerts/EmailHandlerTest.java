package com.amaap.creditcard.alerts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class EmailHandlerTest {

    private EmailHandler emailHandler;

    @BeforeEach
    void setUp() {
        emailHandler = new EmailHandler();
    }


    @Test
    void shouldThrowIllegalArgumentExceptionIfEmptySubjectPassed() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> emailHandler.sendEmailAlert("", "Body content", "abc@mail,com"));

    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfEmptyBodyPassed() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> emailHandler.sendEmailAlert("subject", "", "abc@mail,com"));

    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfEmptyRecipientPassed() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> emailHandler.sendEmailAlert("subject", "Body content", ""));

    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfEmailIsNotCorrect() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> emailHandler.sendEmailAlert("subject", "Body content", "sudhir.t"));
    }

    @Test
    void shouldSendAlertIfValidParametersPassed() {
        String subject = "Test Subject";
        String body = "Test Body";
        String bEmail = "sudhirtakale99@gmail.com";

        assertDoesNotThrow(() -> emailHandler.sendEmailAlert(subject, body, bEmail));
    }


    @Test
    void shouldReturnCorrectTotalAmountOfSpending() {
        Map<String, Integer> spendingMap = new HashMap<>();
        spendingMap.put("Grocery", 100);
        spendingMap.put("Travel", 200);
        spendingMap.put("Shopping", 150);

        Assertions.assertEquals(450, emailHandler.getTotalAmount(spendingMap));
    }


}
