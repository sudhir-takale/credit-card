package com.amaap.creditcard.transactions;

import com.amaap.creditcard.domain.CreditCard;
import com.amaap.creditcard.domain.Customer;
import com.amaap.creditcard.domain.exceptions.InvalidCustomerEmailException;
import com.amaap.creditcard.domain.exceptions.InvalidCustomerIdException;
import com.amaap.creditcard.domain.exceptions.InvalidCustomerNameException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TransactionTest {

    @Test
    void shouldAbleToMakeTransaction() throws InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {
//        Arrange
        CreditCard creditCard = new CreditCard(123456789, Customer.createNewCustomer(1, "John Doe", "john.doe@example.com"));
//        Act
        Transaction transaction = Transaction.makeTransaction(1, LocalDate.now(), "Grocery", creditCard, 50);
//        Assert
        Assertions.assertNotNull(transaction);
        Assertions.assertEquals(1, transaction.getTransactionId());
        Assertions.assertEquals(LocalDate.now(), transaction.getDateOfTransaction());
        Assertions.assertEquals("Grocery", transaction.getCategory());
        Assertions.assertEquals(creditCard, transaction.getCreditCard());
        Assertions.assertEquals(50, transaction.getAmount());
    }

    @Test
    void shouldNotAllowIfTransactionIdIsNegative() {
//        Arrange
        CreditCard creditCard = new CreditCard(123456789, null);
//        Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Transaction(-1, LocalDate.now(), "Grocery", creditCard, 50);
        });
    }

    @Test
    void shouldNotAllowIfDateOfTransactionIsNull() throws InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {
//        Arrange
        Customer customer = Customer.createNewCustomer(1, "Sudhir T", "sudhir@example.com");
        CreditCard creditCard = new CreditCard(123456789, customer);
//        Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Transaction(1, null, "Grocery", creditCard, 50);
        });
    }

    @Test
    void shouldNotAllowIfCategoryIsNull() throws InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {
//        Arrange
        Customer customer = Customer.createNewCustomer(1, "Sudhir T", "sudhirt@gm.com");
        CreditCard creditCard = new CreditCard(123456789, customer);
//        Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Transaction(1, LocalDate.now(), null, creditCard, 10);
        });
    }

    @Test
    void shouldNotAllowIfCreditCardIsNull() {
//        Act & assert
        assertThrows(NullPointerException.class, () -> {
            Transaction.makeTransaction(1, LocalDate.now(), "Grocery", null, 50);

        });
    }

    @Test
    void shouldFailIfAmountIsNegative() throws InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {
//        Arrange
        Customer customer = Customer.createNewCustomer(1, "sudhir t", "sudhir@test.com");
        CreditCard creditCard = new CreditCard(123456789, customer);
//        Assert
        assertThrows(IllegalArgumentException.class, () -> {
            Transaction.makeTransaction(1, LocalDate.now(), "Grocery", creditCard, -50);
        });
    }


}


