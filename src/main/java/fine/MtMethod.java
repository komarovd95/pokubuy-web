package fine;

import java.io.IOException;

public final class MtMethod implements Matching {
    private final String method;

    public MtMethod(final String method) {
        this.method = method;
    }

    @Override
    public boolean matches(final Request request) throws IOException {
        return this.method.equalsIgnoreCase(request.method());
    }
}
