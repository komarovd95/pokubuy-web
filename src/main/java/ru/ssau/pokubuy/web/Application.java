package ru.ssau.pokubuy.web;

import ru.ssau.pokubuy.web.responses.RsNotFound;

import java.util.Arrays;

public final class Application implements Action {
    private final Iterable<Endpoint> endpoints;

    public Application(Endpoint... endpoints) {
        this(Arrays.asList(endpoints));
    }

    public Application(Iterable<Endpoint> endpoints) {
        this.endpoints = endpoints;
    }

    @Override
    public Response act(Request request) {
        for (Endpoint endpoint : this.endpoints) {
            if (request.matches(endpoint)) {
                return endpoint.act(request);
            }
        }

        return new RsNotFound();
    }
}
