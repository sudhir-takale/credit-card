package org.amaap.task.FizzBuzz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FizzBuzzTest {


    FizzBuzz fizzBuzz = new FizzBuzz();


    @Test
    void shouldBeAbleToCreateInstanceOfFizzBuzz() {

        fizzBuzz = new FizzBuzz();

        assertEquals(true, fizzBuzz instanceof FizzBuzz);

    }

    @Test
    void shouldReturnAnEmptyListIfLimitIsZero() {

        List<String> result = fizzBuzz.playGame(0);
        assertEquals(0, result.size());
    }

    @Test
    void shouldReturnAnEmptyListIfLimitIsLessThanZero() {

        assertEquals(0, fizzBuzz.playGame(-1).size());

    }

    @Test
    void shouldAddFizzToListIfNumberIsDivisibleByThree() {
        String arr[] = {"1", "2", "Fizz", "4", "Buzz", "Fizz"};
        List<String> result = new ArrayList<>();
        Collections.addAll(result, arr);
        Assertions.assertEquals(result, fizzBuzz.playGame(6));
    }

    @Test
    void shouldAddBuzzIfNumberIsDivisibleByFive() {
        String arr[] = {"1", "2", "Fizz", "4", "Buzz", "Fizz"};
        List<String> result = new ArrayList<>();
        Collections.addAll(result, arr);
        Assertions.assertEquals(result, fizzBuzz.playGame(6));
    }


    @Test
    void shouldAddFizzBuzzToListIfNumberIsDivisibleByBothThreeAndFive() {

        String arr[] = {"1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz", "11", "Fizz", "13", "14", "FizzBuzz"};
        List<String> result = new ArrayList<>();
        Collections.addAll(result, arr);
        assertEquals(result, fizzBuzz.playGame(15));


    }


}
