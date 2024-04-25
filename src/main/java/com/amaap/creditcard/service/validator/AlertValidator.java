package com.amaap.creditcard.service.validator;

public class AlertValidator {
    public static boolean validate(String messageBody, String emailAddress) {
        return (!messageBody.isEmpty() && messageBody.matches("[0-9]") && emailAddress.matches("^[a-zA-Z0-9_+&*-]+" +
                "(?:\\" +
                ".[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$") && !emailAddress.isEmpty());
    }
}
