package org.amaap.task.FizzBuzz;

import java.util.ArrayList;
import java.util.List;

public class FizzBuzz {
    public List<String> playGame(int limit) {
        List<String> result = new ArrayList<>();

        for (int i = 1; i <= limit; i++) {

            if (i % 3 == 0 && i % 5 == 0) result.add("FizzBuzz");
            else if (i % 3 == 0) result.add("Fizz");
            else if (i % 5 == 0) result.add("Buzz");
            else result.add("" + i);
        }

        return result;
    }
}
