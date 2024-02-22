package org.amaap.task.stringcalculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringCalculatorTest {






    @Test
    void shouldAddANewStringAndReturnSum() {
        String input = "1,2,2";
        String expected = "Sum is : 5.0";
        String result = StringCalculator.add(input);
        assertEquals(expected, result);
    }

    @Test
    void shouldReturnNewLineCharacterFoundAtBeginningOfString() {
        String input = "\n2,3";
        String expected = "Sum is : 5.0";
        String result = StringCalculator.add(input);
        assertEquals(expected, result);
    }

    @Test
    void shouldReturnZeroIfInputIsNull() {
        String input = "";
        String expected = "Sum is : 0.0";
        String result = StringCalculator.add(input);
        assertEquals(expected, result);
    }


    @Test
    void shouldReturnSumOfNumbersPresentInNumberString() {
        String input = "1\n,2\n,3";
        String expected = "Sum is : 6.0";
        String result = StringCalculator.add(input);
        assertEquals(expected, result);
    }

    @Test
    void shouldReturnZeroIfInputIsWhitespace() {
        String input = "    ";
        String expected = "Sum is : 0.0";
        String result = StringCalculator.add(input);
        assertEquals(expected, result);
    }

    @Test
    void shouldHandleCustomDelimiter() {
        String input = "//;\n1;2;3";
        String expected = "Sum is : 6.0";
        String result = StringCalculator.add(input);
        assertEquals(expected, result);
    }


}
