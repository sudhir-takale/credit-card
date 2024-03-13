package org.amaap.task.creditcard.domain.exceptions;

public class InvalidCardNumberException extends Throwable {
    public InvalidCardNumberException(String s) {
        super(s);
    }
}
