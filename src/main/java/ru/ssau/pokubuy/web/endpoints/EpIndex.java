package ru.ssau.pokubuy.web.endpoints;

import ru.ssau.pokubuy.web.Endpoint;
import ru.ssau.pokubuy.web.Request;
import ru.ssau.pokubuy.web.Response;
import ru.ssau.pokubuy.web.matchings.MtAnd;
import ru.ssau.pokubuy.web.matchings.MtExact;

public final class EpIndex extends EpMatching {
    private final Endpoint origin;

    public EpIndex(Endpoint origin) {
        super(new MtAnd(new MtExact("/"), origin));

        this.origin = origin;
    }

    @Override
    public Response act(Request request) {
        return this.origin.act(request);
    }
}
