package com.amaap.creditcard.transactions.validator;

import com.amaap.creditcard.domain.CreditCard;
import com.amaap.creditcard.domain.Customer;
import com.amaap.creditcard.domain.exceptions.InvalidCustomerEmailException;
import com.amaap.creditcard.domain.exceptions.InvalidCustomerIdException;
import com.amaap.creditcard.domain.exceptions.InvalidCustomerNameException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.amaap.creditcard.domain.Customer.createNewCustomer;
import static org.junit.jupiter.api.Assertions.*;

public class TransactionValidatorTest {

    @Test
    void shouldValidateTransactionIdGreaterThanZero() throws InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {
        // Arrange
        int transactionId = 123;
        // Act & Assert
        assertTrue(TransactionValidator.validateTransaction(transactionId, LocalDate.now(), "Groceries", new CreditCard(1212, createNewCustomer(1, "sudhir", "sudhirtak99@gm.com")), 100));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionForInvalidTransactionId() {
        // Arrange
        int transactionId = 0;
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> TransactionValidator.validateTransaction(transactionId, LocalDate.now(), "Groceries", new CreditCard(1212, createNewCustomer(1, "sudhir", "sudhirtak99@gm.com")), 100));
        assertEquals("Transaction ID should be greater than 0", exception.getMessage(), "Exception message should be for invalid Transaction ID");
    }

    @Test
    void shouldValidateDateOfTransactionNotNullAndBeforeToday() throws InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {
        // Arrange
        LocalDate dateOfTransaction = LocalDate.now().minusDays(1);
        // Act & Assert
        assertTrue(TransactionValidator.validateTransaction(123, dateOfTransaction, "Groceries", new CreditCard(1212, createNewCustomer(1, "sudhir", "sudhirtak99@gm.com")), 100));
    }

    @Test
    void shouldValidateCategoryNotNullAndNotEmpty() throws InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {
        // Arrange
        String category = "Groceries";
        // Act & Assert
        assertTrue(TransactionValidator.validateTransaction(123, LocalDate.now(), category, new CreditCard(1212, Customer.createNewCustomer(1, "sudhir", "sudhirtak99@gm.com")), 100));
    }

    @Test
    void shouldValidateCreditCardNotNullAndAssociatedCustomerNotNull() throws InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {
        // Arrange
        CreditCard creditCard = new CreditCard(1212, Customer.createNewCustomer(1, "sudhir", "sudhirtak99@gm.com"));
        // Act & Assert
        assertTrue(TransactionValidator.validateTransaction(123, LocalDate.now(), "Groceries", creditCard, 100));
    }

    @Test
    void shouldValidateAmountGreaterThanZero() throws InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {
        // Act & Assert
        assertTrue(TransactionValidator.validateTransaction(123, LocalDate.now(), "Groceries", new CreditCard(1212, Customer.createNewCustomer(1, "sudhir", "sudhirtak99@gm.com")), 100));
    }

}
