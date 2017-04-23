package fine;

import java.io.IOException;
import java.util.Optional;

public abstract class RtWrap implements Route {
    protected final Route origin;

    protected RtWrap(final Route origin) {
        this.origin = origin;
    }

    @Override
    public Optional<Response> enter(final Request request) throws IOException {
        return this.origin.enter(request);
    }
}
