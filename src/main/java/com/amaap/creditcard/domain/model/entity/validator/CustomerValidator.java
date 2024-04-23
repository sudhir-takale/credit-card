package com.amaap.creditcard.domain.model.entity.validator;

public class CustomerValidator {
    public static boolean validate(String name, String emailAddress) {

        return validateName(name) && validateEmail(emailAddress);
    }

    private static boolean validateEmail(String email) {
        return email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$") && !email.isEmpty();
    }

    private static boolean validateName(String name) {
        return !(name.matches(".*\\d.*") || name.matches(".*[!@#$%^&*()_+{}|:\"<>?/\\[\\];',.\\\\-].*") || name.isEmpty());
    }
}
