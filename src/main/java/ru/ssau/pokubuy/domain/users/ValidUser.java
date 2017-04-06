package ru.ssau.pokubuy.domain.users;

import ru.ssau.pokubuy.core.validation.RegExpValidation;
import ru.ssau.pokubuy.core.validation.ValidationException;
import ru.ssau.pokubuy.core.validation.VerboseValidation;

public final class ValidUser implements User {
    private final User origin;

    public ValidUser(User origin) {
        this.origin = origin;
    }

    @Override
    public String name() throws ValidationException {
        return new VerboseValidation<>(
            new RegExpValidation("^[a-zA-Z0-9]{4,20}$"),
            "Имя пользователя должно содержать от 4 до 20 латинских символов и цифр"
        ).validate(this.origin.name());
    }

    @Override
    public String password() throws ValidationException {
        return new VerboseValidation<>(
            new RegExpValidation("^[a-zA-Z0-9]{4,20}$"),
            "Пароль должен содержать от 4 до 20 латинских символов и цифр"
        ).validate(this.origin.password());
    }
}
