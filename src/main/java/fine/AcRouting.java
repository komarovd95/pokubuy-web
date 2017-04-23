package fine;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public final class AcRouting implements Action {
    private final Iterable<Route> routes;

    public AcRouting(final Route... routes) {
        this(Arrays.asList(routes));
    }

    public AcRouting(final Iterable<Route> routes) {
        this.routes = routes;
    }

    @Override
    public Response act(final Request request) throws IOException {
        for (Route route : this.routes) {
            Optional<Response> entered = route.enter(request);

            if (entered.isPresent()) {
                return entered.get();
            }
        }

        return new RsNotFound();
    }
}
