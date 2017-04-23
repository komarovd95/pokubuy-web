package fine;

import java.io.Serializable;

public final class ConstantUser implements User, Serializable {
    private final long id;
    private final String name;
    private final String password;
    private final String avatarPath;
    private final Role role;

    public ConstantUser(final long id, final String name, final String password,
                        final String avatarPath, final Role role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.avatarPath = avatarPath;
        this.role = role;
    }

    @Override
    public long id() {
        return this.id;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public String password() {
        return this.password;
    }

    @Override
    public String avatarPath() {
        return this.avatarPath;
    }

    @Override
    public Role role() {
        return this.role;
    }
}
