package fine;

import ru.ssau.pokubuy.core.validation.ValidationException;

public interface User {
    long id();
    String name() throws ValidationException;
    String password() throws ValidationException;
    String avatarPath();
    Role role();
}
