package fine;

import java.io.IOException;

@FunctionalInterface
public interface Action {
    Response act(Request request) throws IOException;
}
