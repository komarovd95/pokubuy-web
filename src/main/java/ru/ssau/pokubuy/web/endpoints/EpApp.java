package ru.ssau.pokubuy.web.endpoints;

import ru.ssau.pokubuy.web.Endpoint;
import ru.ssau.pokubuy.web.Request;
import ru.ssau.pokubuy.web.Response;

import java.util.Arrays;

public final class EpApp implements Endpoint {
    private final Iterable<Endpoint> endpoints;

    public EpApp(Endpoint... endpoints) {
        this(Arrays.asList(endpoints));
    }

    public EpApp(Iterable<Endpoint> endpoints) {
        this.endpoints = endpoints;
    }

    @Override
    public boolean matches(String method, String path) {
        return false;
    }

    @Override
    public Response act(Request request) {
        return null;
    }
}
