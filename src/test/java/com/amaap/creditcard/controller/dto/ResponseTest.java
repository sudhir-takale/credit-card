package com.amaap.creditcard.controller.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseTest {

    @Test
    public void shouldReturnTrueForEqualResponses() {
        // arrange & act
        Response response1 = new Response(HttpsStatus.OK, "Success");
        Response response2 = new Response(HttpsStatus.OK, "Success");

        // assert
        assertTrue(response1.equals(response2));
    }

    @Test
    public void shouldReturnFalseForDifferentHttpsStatus() {
        // arrange & act
        Response response1 = new Response(HttpsStatus.OK, "Success");
        Response response2 = new Response(HttpsStatus.NOT_FOUND, "Success");

        // assert
        assertFalse(response1.equals(response2));
    }

    @Test
    public void shouldReturnFalseForDifferentResponseMessages() {
        // arrange & act
        Response response1 = new Response(HttpsStatus.OK, "Success");
        Response response2 = new Response(HttpsStatus.OK, "Error");

        // assert
        assertFalse(response1.equals(response2));
    }

    @Test
    public void shouldReturnFalseForNullObject() {
        // arrange & act
        Response response = new Response(HttpsStatus.OK, "Success");

        // assert
        assertFalse(response == null);

    }

    @Test
    public void shouldReturnFalseForDifferentClasses() {
        // arrange & act
        Response response = new Response(HttpsStatus.OK, "Success");

        // assert
        assertFalse(response.equals("Success"));
    }

    @Test
    public void shouldReturnConsistentHashCode() {
        // arrange
        Response response = new Response(HttpsStatus.OK, "Success");

        // act
        int hashCode1 = response.hashCode();
        int hashCode2 = response.hashCode();

        // assert
        assertEquals(hashCode1, hashCode2);
    }

}