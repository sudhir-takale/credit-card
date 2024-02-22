package org.amaap.task.stringcalculator;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StringCalculatorTDDTest {


    ModernStringCalculator modernStringCalculator = new ModernStringCalculator();

    @Test
    void shouldAbleToCreateInstanceOfStringCalculatorTDD() {

        ModernStringCalculator modernStringCalculator = new ModernStringCalculator();
        assertTrue(modernStringCalculator instanceof ModernStringCalculator);
    }

    @Test
    void shouldReturnZeroIfInputIsEmptyString() {


        assertEquals("0", modernStringCalculator.addString(""));


    }

    


}
