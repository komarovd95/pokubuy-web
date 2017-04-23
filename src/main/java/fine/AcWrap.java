package fine;

import java.io.IOException;

public abstract class AcWrap implements Action {
    protected final Action origin;

    protected AcWrap(final Action origin) {
        this.origin = origin;
    }

    @Override
    public Response act(final Request request) throws IOException {
        return this.origin.act(request);
    }
}
