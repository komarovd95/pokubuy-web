package ru.ssau.pokubuy.core.validation;

@FunctionalInterface
public interface Validation<T> {
    T validate(T t) throws ValidationException;
}
