package org.amaap.task.stringcalculator;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class StringCalculator {

    static String add(String number) {

        String delimiter = ",";
        ArrayList<String> negativeNum = new ArrayList<>();
        ArrayList<String> errors = new ArrayList<>();
       number =  number.trim();
        if (number.isEmpty()) {
            return "Sum is : 0.0";
        }

        number = number.trim();

        if (number.startsWith("//")) {
            int delimiterEndIndex = number.indexOf("\n");
            delimiter = number.substring(2, delimiterEndIndex);
            number = number.substring(delimiterEndIndex + 1);
        }

        if (number.endsWith(delimiter)) {
            errors.add("Number expected but EOF found.");
        }

        String arrNumber[] = number.split(Pattern.quote(delimiter));

        for (String num : arrNumber) {
            checkNegativeNumbers(num, negativeNum, errors, number, delimiter);
        }

        if (!negativeNum.isEmpty()) {
            return "Negative not allowed: " + String.join(", ", negativeNum);
        }

        StringBuilder result = new StringBuilder("Sum is : ");
        float sum = calculateSum(arrNumber);
        result.append(sum);

        if (!errors.isEmpty()) {
            result.append("\nErrors:\n");
            for (String errorMsg : errors) {
                result.append(errorMsg).append("\n");
            }
        }

        return result.toString();
    }

    static void checkNegativeNumbers(String string, ArrayList<String> arr, ArrayList<String> errors, String number,
                                     String delimiter) {
        if (string.startsWith("\n")) {
            errors.add("Number expected but '\\n' found at position " + number.indexOf("\n"));
        }

        for (int i = 0; i < string.length(); i++) {
            String str = String.valueOf(string.charAt(i));

            if (string.charAt(i) == '-' && i < string.length() - 1) {
                errors.add("Negative not allowed :" + string.substring(i, i + 2));
            } else if (!str.equals(delimiter) && !str.equals("\n") && !Character.isDigit(string.charAt(i))
                    && !str.equals("-") && !str.equals(".")) {
                errors.add("'" + delimiter + "' expected but '" + string.charAt(i) + "' found at position "
                        + number.indexOf(string.charAt(i)) + ".");
            }
        }
    }

    static float calculateSum(String[] numbers) {
        float sum = 0.0f;
        for (String number : numbers) {
            float num = Float.parseFloat(number);
            sum += num;
        }
        return sum;
    }

    public static class StringCalculatorTDD {
    }

    public static class ModernStringCalculator {
    }
}
