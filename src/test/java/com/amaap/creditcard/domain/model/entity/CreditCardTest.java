package com.amaap.creditcard.domain.model.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreditCardTest {

    @Test
    public void shouldReturnTrueForEqualObjects() {
        // arrange & act
        CreditCard card1 = new CreditCard(new Customer("Sudhir", "Sudhirtakale@gmail.com"));
        CreditCard card2 = new CreditCard(new Customer("Sudhir", "Sudhirtakale@gmail.com"));

        // assert
        assertTrue(card1.equals(card2));
    }

    @Test
    public void shouldReturnFalseForDifferentIds() {
        // arrange & act
        CreditCard card1 = new CreditCard(new Customer("Sudhir Takale", "Sudhirtakale@gmail.com"));
        CreditCard card2 = new CreditCard(new Customer("Sudhir", "Sudhirtakale@gmail.com"));

        // assert
        assertFalse(card1.equals(card2));
    }

    @Test
    public void shouldReturnTrueForSameId() {
        // arrange & act
        CreditCard card1 = new CreditCard(new Customer("Sudhir", "Sudhirtakale@gmail.com"));
        CreditCard card2 = new CreditCard(new Customer("Sudhir", "Sudhirtakale@gmail.com"));

        // assert
        assertTrue(card1.equals(card2));
    }

    @Test
    public void shouldReturnConsistentHashCode() {
        // arrange
        CreditCard card = new CreditCard(new Customer("Sudhir", "Sudhirtakale@gmail.com"));

        // act
        int hashCode1 = card.hashCode();
        int hashCode2 = card.hashCode();

        // assert
        assertEquals(hashCode1, hashCode2);
    }

}