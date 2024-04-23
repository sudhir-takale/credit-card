package com.amaap.creditcard.service.exception;

public class TransactionNotFoundException extends Throwable {
    public TransactionNotFoundException(String message) {
        super(message);
    }
}
