package fine;

public final class PcUserCredentials implements Principal {
    private final String name;
    private final String password;

    public PcUserCredentials(final String name, final String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public String identity() {
        return this.name + ":" + this.password;
    }

    @Override
    public boolean hasRole(Role role) {
        return false;
    }
}
