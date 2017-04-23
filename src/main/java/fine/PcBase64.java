package fine;

import java.util.Base64;

public final class PcBase64 implements Principal {
    private final Principal origin;

    public PcBase64(final Principal origin) {
        this.origin = origin;
    }

    @Override
    public String identity() {
        return Base64.getEncoder().encodeToString(this.origin.identity().getBytes());
    }

    @Override
    public boolean hasRole(Role role) {
        return this.origin.hasRole(role);
    }
}
