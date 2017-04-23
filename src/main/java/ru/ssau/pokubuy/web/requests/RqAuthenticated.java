package ru.ssau.pokubuy.web.requests;

import ru.ssau.pokubuy.web.*;
import ru.ssau.pokubuy.web.attributes.AbSignedUser;
import ru.ssau.pokubuy.web.responses.RsUnauthorized;

import java.io.IOException;

public final class RqAuthenticated implements Request {
    private final transient Request origin;
    private final Authentication authentication;

    public RqAuthenticated(Request origin, Authentication authentication) {
        this.origin = origin;
        this.authentication = authentication;
    }

    @Override
    public Response forward(String path) {
        try {
            return this.constructAuthenticatedRequest().forward(path);
        } catch (IOException e) {
            return new RsUnauthorized();
        }
    }

    @Override
    public Response serve() {
        try {
            return this.constructAuthenticatedRequest().serve();
        } catch (IOException e) {
            return new RsUnauthorized();
        }
    }

    private Request constructAuthenticatedRequest() throws IOException {
        String authenticated = this.authentication.authenticated();

        return new RqAttributed(this.origin, new AbSignedUser(authenticated));
    }

    @Override
    public String header(String name) {
        return this.origin.header(name);
    }

    @Override
    public boolean matches(Matching matching) {
        return this.origin.matches(matching);
    }
}
