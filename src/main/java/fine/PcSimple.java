package fine;

import java.util.Objects;

public final class PcSimple implements Principal {
    private final String name;
    private final String pass;
    private final Role role;

    public PcSimple(final String name, final String pass, final Role role) {
        this.name = name;
        this.pass = pass;
        this.role = role;
    }

    @Override
    public String identity() {
        return this.name + ":" + this.pass + ":" + this.role;
    }

    @Override
    public boolean hasRole(final Role role) {
        return this.role == role;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        } else if (o == null || !(o instanceof Principal)) {
            return false;
        }

        Principal principal = (Principal) o;

        return Objects.equals(this.identity(), principal.identity())
                && principal.hasRole(this.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, pass, role);
    }
}
