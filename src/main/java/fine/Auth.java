package fine;

import java.io.IOException;
import java.util.Optional;

public interface Auth {
    Optional<Principal> authenticated(Request request) throws IOException;
    Response authenticate(Response origin, Principal principal) throws IOException, AuthException;
    Response leave(Response origin) throws IOException;
}
