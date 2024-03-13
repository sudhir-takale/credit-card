package org.amaap.task.creditcard.domain.validators;

public class EmailValidator {


    public static boolean validate(String email) {
        return checkIfEmailIsNotEmpty(email) && checkIfEmailIsValid(email);
    }

    private static boolean checkIfEmailIsValid(String email) {
        return email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    }

    private static boolean checkIfEmailIsNotEmpty(String email) {
        return !email.isEmpty();
    }
}


