package ru.ssau.pokubuy.domain.users;

import ru.ssau.pokubuy.core.validation.ValidationException;

final class FakeUser implements User {
    private final String fake;

    FakeUser(String fake) {
        this.fake = fake;
    }

    @Override
    public String name() throws ValidationException {
        return this.fake;
    }

    @Override
    public String password() throws ValidationException {
        return this.fake;
    }
}
