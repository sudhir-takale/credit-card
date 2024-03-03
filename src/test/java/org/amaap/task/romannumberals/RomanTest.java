package org.amaap.task.romannumberals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class RomanTest {

    @Test
    void shouldThrowExceptionIfInputIsLessThanOrEqualToZero() throws Exception {

        Roman roman = new Roman();
        assertThrows(IllegalInputException.class, () ->
                roman.convert(0)
        );

    }

    @Test
    void shouldReturnRomanNumeralForGivenNumber() throws Exception {

        Roman roman = new Roman();
        assertEquals("I", roman.convert(1));
    }

    @Test
    void shouldReturnRomanNumeralForGivenInputNumber() throws Exception {
        Roman roman = new Roman();
        Assertions.assertEquals("VII", roman.convert(7));
        Assertions.assertEquals("XCIX", roman.convert(99));
    }


}
