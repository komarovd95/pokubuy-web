package ru.ssau.pokubuy.core.validation;

public final class VerboseValidation<T> implements Validation<T> {
    private final Validation<T> origin;
    private final String message;

    public VerboseValidation(Validation<T> origin, String message) {
        this.origin = origin;
        this.message = message;
    }

    @Override
    public T validate(T t) throws ValidationException {
        try {
            return this.origin.validate(t);
        } catch (ValidationException e) {
            throw new ValidationException(this.message, e);
        }
    }
}
