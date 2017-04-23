package fine;

import java.io.IOException;
import java.util.Optional;

public final class RtFallback implements Route {
    private final Action action;

    public RtFallback(final Action action) {
        this.action = action;
    }

    @Override
    public Optional<Response> enter(final Request request) throws IOException {
        return Optional.of(this.action.act(request));
    }
}
