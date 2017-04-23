package fine;

import ru.ssau.pokubuy.core.validation.RegExpValidation;
import ru.ssau.pokubuy.core.validation.ValidationException;
import ru.ssau.pokubuy.core.validation.VerboseValidation;

public final class ValidUser extends UserWrap {
    public ValidUser(final User origin) {
        super(origin);
    }

    @Override
    public String name() throws ValidationException {
        return new VerboseValidation<>(
            new RegExpValidation("^[a-zA-Z0-9]{4,20}$"),
                "Имя пользователя должно содержать от 4 до 20 латинских символов и цифр"
        ).validate(super.name());
    }

    @Override
    public String password() throws ValidationException {
        return new VerboseValidation<>(
            new RegExpValidation("^[a-zA-Z0-9]{4,20}$"),
                "Пароль должен содержать от 4 до 20 латинских символов и цифр"
        ).validate(super.password());
    }
}
