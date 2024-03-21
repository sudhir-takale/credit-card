package com.amaap.creditcard.alerts;

public class EmailValidator {


    public static boolean validate(String subject, String content, String emailAddress) throws IllegalArgumentException {
        if (subject.isEmpty() || content.isEmpty() || emailAddress.isEmpty())
            throw new IllegalArgumentException("Check your arguments");

        return true;
    }
}

