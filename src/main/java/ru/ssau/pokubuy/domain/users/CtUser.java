package ru.ssau.pokubuy.domain.users;

import ru.ssau.pokubuy.core.validation.ValidationException;

public final class CtUser implements User {
    private final String name;
    private final String password;

    public CtUser(final String name, final String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public String name() throws ValidationException {
        return this.name;
    }

    @Override
    public String password() throws ValidationException {
        return this.password;
    }
}
