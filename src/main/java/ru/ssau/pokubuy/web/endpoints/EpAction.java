package ru.ssau.pokubuy.web.endpoints;

import ru.ssau.pokubuy.web.*;

public final class EpAction extends EpMatching {
    private final Action origin;

    protected EpAction(Matching matching, Action origin) {
        super(matching);

        this.origin = origin;
    }

    @Override
    public Response act(Request request) {
        return this.origin.act(request);
    }
}
