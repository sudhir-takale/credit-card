package org.amaap.task.romannumberals;

public class Roman {


    public String convert(int number) throws Exception {

        if (number <= 0 || number > 9999) throw new IllegalInputException("Input is not valid");

        StringBuilder sb = new StringBuilder();

        String[] values = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] keys = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

        while (number != 0) {
            for (int i = 0; i < keys.length; i++) {

                if (keys[i] <= number) {

                    number -= keys[i];
                    sb.append(values[i]);
                }
            }

        }
        return sb.toString();

    }
}
