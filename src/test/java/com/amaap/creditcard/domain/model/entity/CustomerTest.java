package com.amaap.creditcard.domain.model.entity;

import com.amaap.creditcard.domain.model.entity.exception.InvalidCustomerDataException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void shouldBeAbleToCreateCustomerWithValidArguments() throws InvalidCustomerDataException {
        // arrange
        Customer customer = Customer.create("sudhir Takale", "sudhirtakale@gmail.com");

        // assert
        assertEquals("sudhir Takale", customer.getName());
        assertEquals("sudhirtakale@gmail.com", customer.getEmailAddress());
    }

    @Test
    void shouldThrowExceptionWhenInvalidArgumentsPassed() {
        // assert
        assertThrows(InvalidCustomerDataException.class, () -> Customer.create("", "sudhir@gmail.com"));
        assertThrows(InvalidCustomerDataException.class, () -> Customer.create("Sudhir Taklae", "sudhir.com"));
    }


    @Test
    public void shouldReturnTrueForEqualCustomers() {
        // arrange & act
        Customer customer1 = new Customer("Sudhir Takale", "sudhirtakaledummy@gmail.com");
        Customer customer2 = new Customer("Sudhir Takale", "sudhirtakaledummy@gmail.com");

        // assert
        assertTrue(customer1.equals(customer2));
    }

    @Test
    public void shouldReturnFalseForDifferentNames() {
        // arrange & act
        Customer customer1 = new Customer("Sudhir Takale", "sudhirtakaledummy@gmail.com");
        Customer customer2 = new Customer("Jane Smith", "sudhirtakaledummy@gmail.com");

        // assert
        assertFalse(customer1.equals(customer2));
    }

    @Test
    public void shouldReturnFalseForDifferentEmails() {
        // arrange & act
        Customer customer1 = new Customer("Sudhir Takale", "sudhirtakaledummy@gmail.com");
        Customer customer2 = new Customer("Sudhir Takale", "jane@example.com");

        // assert
        assertFalse(customer1.equals(customer2));
    }


    @Test
    public void shouldReturnFalseForDifferentClasses() {
        // arrange & act
        Customer customer = new Customer("Sudhir Takale", "sudhirtakaledummy@gmail.com");

        // assert
        assertFalse(customer.equals("Sudhir Takale"));
    }

    @Test
    public void shouldReturnConsistentHashCode() {
        // arrange
        Customer customer = new Customer("Sudhir Takale", "sudhirtakaledummy@gmail.com");

        // act
        int hashCode1 = customer.hashCode();
        int hashCode2 = customer.hashCode();

        // assert
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    public void shouldReturnSameHashCodeForEqualCustomers() throws InvalidCustomerDataException {
        // arrange & act
        Customer customer1 = Customer.create("Sudhir Takale", "sudhirtakaledummy@gmail.com");
        Customer customer2 = Customer.create("Sudhir Takale", "sudhirtakaledummy@gmail.com");

        // assert
        assertEquals(customer1.hashCode(), customer2.hashCode());
    }

}