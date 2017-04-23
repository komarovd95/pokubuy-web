package ru.ssau.pokubuy.web.endpoints;

import ru.ssau.pokubuy.web.Endpoint;
import ru.ssau.pokubuy.web.Request;
import ru.ssau.pokubuy.web.Response;

import java.util.Date;

public final class EpLastModified implements Endpoint {
    private final Endpoint origin;
    private final long lastModified;

    public EpLastModified(Endpoint origin) {
        this(origin, new Date().getTime());
    }

    public EpLastModified(Endpoint origin, long lastModified) {
        this.origin = origin;
        this.lastModified = lastModified;
    }

    @Override
    public boolean matches(String method, String path) {
        return this.origin.matches(method, path);
    }

    @Override
    public Response act(Request request) {
        String header = request.header("If-Modified-Since");
        return this.origin.act(request);
    }
}
