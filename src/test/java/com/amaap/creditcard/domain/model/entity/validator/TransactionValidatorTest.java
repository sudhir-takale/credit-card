package com.amaap.creditcard.domain.model.entity.validator;

import com.amaap.creditcard.domain.model.valueobject.Category;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TransactionValidatorTest {
    @Test
    void shouldReturnFalseForInvalidParameters() {
        // assert
        assertFalse(TransactionValidator.validate(-3, LocalDate.now(), Category.TRAVEL, 34.4));
        assertFalse(TransactionValidator.validate(1, LocalDate.now(), null, 34.4));
        assertFalse(TransactionValidator.validate(-1, LocalDate.now().minusDays(23), Category.TRAVEL, 34.4));
        assertFalse(TransactionValidator.validate(1, LocalDate.now().minusDays(23), Category.TRAVEL, -12));
        assertFalse(TransactionValidator.validate(1, LocalDate.now(), Category.TRAVEL, -12));
    }

    @Test
    void shouldReturnTrueWhenValidTransaction() {
        // assert
        assertTrue(TransactionValidator.validate(3, LocalDate.now().minusDays(1), Category.TRAVEL, 34.4));
        assertTrue(TransactionValidator.validate(1, LocalDate.now(), Category.SHOPPING, 34.4));

    }


}