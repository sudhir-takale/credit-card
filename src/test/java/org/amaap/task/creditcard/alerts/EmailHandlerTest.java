package org.amaap.task.creditcard.alerts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EmailHandlerTest {

    private EmailHandler emailHandler;

    @BeforeEach
    void setUp() {
        emailHandler = new EmailHandler();
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

        assertEquals(450, emailHandler.getTotalAmount(spendingMap));
    }
}
