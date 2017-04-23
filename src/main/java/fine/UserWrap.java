package fine;

import ru.ssau.pokubuy.core.validation.ValidationException;

public class UserWrap implements User {
    private final User origin;

    protected UserWrap(final User origin) {
        this.origin = origin;
    }

    @Override
    public long id() {
        return this.origin.id();
    }

    @Override
    public String name() throws ValidationException {
        return this.origin.name();
    }

    @Override
    public String password() throws ValidationException {
        return this.origin.password();
    }

    @Override
    public String avatarPath() {
        return this.origin.avatarPath();
    }

    @Override
    public Role role() {
        return this.origin.role();
    }
}
