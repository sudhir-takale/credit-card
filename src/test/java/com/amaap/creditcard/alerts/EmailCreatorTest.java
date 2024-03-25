package com.amaap.creditcard.alerts;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmailCreatorTest {

    @Test
    void shouldAbleToCreateEmailWithValidArguments() {
        // Arrange
        EmailCreator emailCreator = new EmailCreator("Subject", " Body", "Credit card");
        // Act
        EmailCreator customEmail = emailCreator.createCustomEmail();
        // Assert
        Assertions.assertNotNull(customEmail);
        Assertions.assertEquals("Sudhir", customEmail.getSubject());
        Assertions.assertEquals("How are you ", customEmail.getBodyContent());
        Assertions.assertEquals("The credit card", customEmail.getFooter());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfDataIsNotValid() {
        // act & assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> new EmailCreator(null, "Body", "Credit Card"));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfBodyContentIsNull() {
        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> new EmailCreator("Subject", null, "Credit card"));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfFooterIsNull() {
        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> new EmailCreator(" Subject", " Body", null));
    }
}
