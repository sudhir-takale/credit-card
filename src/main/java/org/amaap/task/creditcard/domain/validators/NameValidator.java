package org.amaap.task.creditcard.domain.validators;

public class NameValidator {


    public static boolean validate(String name) {
        return checkIfNameIsNotEmpty(name) && checkIfNameIsValid(name);
    }

    private static boolean checkIfNameIsNotEmpty(String name) {
        return !name.isEmpty();
    }

    private static boolean checkIfNameIsValid(String name) {
        return !(name.matches(".*\\d.*") || name.matches(".*[!@#$%^&*()_+{}|:\"<>?/\\[\\];',.\\\\-].*"));
    }

}
