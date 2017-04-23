package fine;

import java.io.IOException;
import java.util.Optional;

public final class AcSignOut implements Action {
    private final transient Auth auth;

    public AcSignOut(final Auth auth) {
        this.auth = auth;
    }

    @Override
    public Response act(final Request request) throws IOException {
        Optional<Principal> authenticated = this.auth.authenticated(request);
        if (authenticated.isPresent()) {
            return this.auth.leave(new RsRedirect());
        }

        return new RsRedirect();
    }
}
