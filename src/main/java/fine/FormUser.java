package fine;

import ru.ssau.pokubuy.core.validation.ValidationException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class FormUser implements User {
    private final transient Request request;
    private final Map<String, String> formData = new HashMap<>();
    private volatile boolean parsed;

    public FormUser(final Request request) {
        this.request = request;
    }

    @Override
    public long id() {
        Optional<String> id = this.formValue("id");
        if (id.isPresent()) {
            return Long.parseLong(id.get());
        } else {
            throw new RuntimeException("No \"id\" field in given form");
        }
    }

    @Override
    public String name() throws ValidationException {
        Optional<String> username = this.formValue("username");
        if (username.isPresent()) {
            return username.get();
        } else {
            throw new ValidationException("Имя не может быть пустым");
        }
    }

    @Override
    public String password() throws ValidationException {
        Optional<String> password = this.formValue("password");
        if (password.isPresent()) {
            return password.get();
        } else {
            throw new ValidationException("Пароль не может быть пустым");
        }
    }

    @Override
    public String avatarPath() {
        Optional<String> avatar = this.formValue("avatar");
        if (avatar.isPresent()) {
            return avatar.get();
        } else {
            throw new RuntimeException("No \"avatar\" field in given form");
        }
    }

    @Override
    public Role role() {
        Optional<String> role = this.formValue("role");
        if (role.isPresent()) {
            return Role.valueOf(role.get());
        } else {
            throw new RuntimeException("No \"role\" field in given form");
        }
    }

    private Optional<String> formValue(final String name) {
        try {
            synchronized (this) {
                if (!parsed) {
                    new RqForm(this.request).values()
                            .forEach(this.formData::putIfAbsent);
                    parsed = true;
                }
            }

            return Optional.ofNullable(this.formData.get(name));
        } catch (IOException e) {
            return Optional.empty();
        }
    }
}
