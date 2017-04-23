package ru.ssau.pokubuy.web.endpoints;

import ru.ssau.pokubuy.web.Endpoint;
import ru.ssau.pokubuy.web.Request;
import ru.ssau.pokubuy.web.Response;

public final class EpAuthenticated implements Endpoint {
    private final Endpoint origin;

    public EpAuthenticated(Endpoint origin) {
        this.origin = origin;
    }

    @Override
    public boolean matches(String method, String path) {
        return this.origin.matches(method, path);
    }

    @Override
    public Response act(Request request) {
        return this.origin.act(request);
    }
}
