package fine;

public final class PcIdentity implements Principal {
    @Override
    public String identity() {
        return null;
    }

    @Override
    public boolean hasRole(Role role) {
        return false;
    }
}
