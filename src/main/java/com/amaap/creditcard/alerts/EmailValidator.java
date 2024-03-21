package com.amaap.creditcard.alerts;

import java.util.regex.Pattern;

public class EmailValidator {


    public static boolean validate(String subject, String content, String emailAddress) throws IllegalArgumentException {
        if (subject.isEmpty() || content.isEmpty() || emailAddress.isEmpty())
            throw new IllegalArgumentException("Check your arguments");

        return true;
    }

    public static void validateEmail(String subject, String body, String recipientEmail) {
        if (subject == null || subject.isEmpty()) {
            throw new IllegalArgumentException("Subject cannot be empty");
        }
        if (body == null || body.isEmpty()) {
            throw new IllegalArgumentException("Body content cannot be empty");
        }
        if (recipientEmail == null || recipientEmail.isEmpty() || !Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", recipientEmail)) {
            throw new IllegalArgumentException("Recipient email cannot be empty");
        }

    }
}

