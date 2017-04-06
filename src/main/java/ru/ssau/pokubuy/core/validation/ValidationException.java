package ru.ssau.pokubuy.core.validation;

public final class ValidationException extends Exception {
    public ValidationException() {}

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
