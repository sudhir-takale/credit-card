package com.amaap.creditcard.alerts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@Tag("integration")
class EmailHandlerTest {

    private EmailHandler emailHandler;

    @BeforeEach
    void setUp() {
        emailHandler = new EmailHandler();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfEmptySubjectPassed() {
        //Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> emailHandler.sendEmailAlert("", "Body content", "abc@mail,com"));

    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfEmptyBodyPassed() {
        //Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> emailHandler.sendEmailAlert("subject", "", "abc@mail,com"));

    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfEmptyRecipientPassed() {
        //Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> emailHandler.sendEmailAlert("subject", "Body content", ""));

    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfEmailIsNotCorrect() {
        //Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> emailHandler.sendEmailAlert("subject", "Body content", "sudhir.t"));
    }

    @Test
    void shouldValidateEmailWithValidParameters() {
        //Act & Assert
        assertDoesNotThrow(() -> emailHandler.sendEmailAlert("subject", "body", "abc@mail.com"));
    }

    @Test
    void shouldSendAlertIfValidParametersPassed() {
        //Act & Assert
        String subject = "Test Subject";
        String body = "Test Body";
        String bEmail = "sudhirtakale99@gmail.com";
        assertDoesNotThrow(() -> emailHandler.sendEmailAlert(subject, body, bEmail));
    }


    @Test
    void shouldReturnCorrectTotalAmountOfSpending() {
        //Act
        Map<String, Integer> spendingMap = new HashMap<>();
        spendingMap.put("Grocery", 100);
        spendingMap.put("Travel", 200);
        spendingMap.put("Shopping", 150);
        // Assert
        Assertions.assertEquals(450, emailHandler.getTotalAmount(spendingMap));
    }

    @Test
    void shouldCreateCustomEmailWithCorrectValues() {
        // Arrange
        String expectedSubject = "Custom subject is passed";
        String expectedBody = "Custom Body is passed";
        String expectedFooter = "Custom Footer is passed";
        // Act
        EmailCreator emailCreator = emailHandler.createCustomEmail();
        // Assert
        Assertions.assertNotNull(emailCreator);
        Assertions.assertEquals(expectedSubject, emailCreator.getSubject());
        Assertions.assertEquals(expectedBody, emailCreator.getBodyContent());
        Assertions.assertEquals(expectedFooter, emailCreator.getFooter());
    }


}
