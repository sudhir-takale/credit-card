package org.amaap.task.creditcard.transactions;

import org.amaap.task.creditcard.domain.CreditCard;
import org.amaap.task.creditcard.domain.Customer;
import org.amaap.task.creditcard.domain.exceptions.InvalidCustomerEmailException;
import org.amaap.task.creditcard.domain.exceptions.InvalidCustomerIdException;
import org.amaap.task.creditcard.domain.exceptions.InvalidCustomerNameException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TransactionTest {

    @Test
    void shouldAbleToMakeTransaction() throws InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {

        CreditCard creditCard = new CreditCard(123456789, Customer.createNewCustomer(1, "John Doe", "john.doe@example.com"));
        Transaction transaction = Transaction.makeTransaction(1, LocalDate.now(), "Grocery", creditCard, 50);
        Assertions.assertNotNull(transaction);
        Assertions.assertEquals(1, transaction.getTransactionId());
        Assertions.assertEquals(LocalDate.now(), transaction.getDateOfTransaction());
        Assertions.assertEquals("Grocery", transaction.getCategory());
        Assertions.assertEquals(creditCard, transaction.getCreditCard());
        Assertions.assertEquals(50, transaction.getAmount());
    }

    @Test
    void shouldNotAllowIfTransactionIdIsNegative() {

        CreditCard creditCard = new CreditCard(123456789, null);

        assertThrows(IllegalArgumentException.class, () -> {
            new Transaction(-1, LocalDate.now(), "Grocery", creditCard, 50);
        });
    }

    @Test
    void shouldNotAllowIfDateOfTransactionIsNull() throws InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {

        Customer customer = Customer.createNewCustomer(1, "John Doe", "john.doe@example.com");
        CreditCard creditCard = new CreditCard(123456789, customer);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Transaction(1, null, "Grocery", creditCard, 50);
        });
    }

    @Test
    void shouldNotAllowIfCategoryIsNull() throws InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {
        Customer customer = Customer.createNewCustomer(1, "John Doe", "john.doe@example.com");
        CreditCard creditCard = new CreditCard(123456789, customer);

        assertThrows(IllegalArgumentException.class, () -> {
            new Transaction(1, LocalDate.now(), null, creditCard, 10);
        });
    }

    @Test
    void shouldNotAllowIfCreditCardIsNull() {

        assertThrows(NullPointerException.class, () -> {
            Transaction.makeTransaction(1, LocalDate.now(), "Grocery", null, 50);

        });
    }

    @Test
    void shouldFailIfAmountIsNegative() throws InvalidCustomerIdException, InvalidCustomerNameException, InvalidCustomerEmailException {

        Customer customer = Customer.createNewCustomer(1, "John Doe", "john.doe@example.com");

        CreditCard creditCard = new CreditCard(123456789, customer);


        assertThrows(IllegalArgumentException.class, () -> {
            Transaction.makeTransaction(1, LocalDate.now(), "Grocery", creditCard, -50);
        });
    }


}


