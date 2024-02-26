package org.amaap.task.car;

public class InsufficientFuelException extends Throwable {
    public InsufficientFuelException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
