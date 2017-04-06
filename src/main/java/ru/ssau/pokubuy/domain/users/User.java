package ru.ssau.pokubuy.domain.users;

import ru.ssau.pokubuy.core.validation.ValidationException;

public interface User {
    String name() throws ValidationException;
    String password() throws ValidationException;
}
