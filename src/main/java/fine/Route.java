package fine;

import java.io.IOException;
import java.util.Optional;

@FunctionalInterface
public interface Route {
    Optional<Response> enter(Request request) throws IOException;
}
