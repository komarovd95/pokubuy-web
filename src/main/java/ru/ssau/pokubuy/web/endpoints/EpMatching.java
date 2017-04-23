package ru.ssau.pokubuy.web.endpoints;

import ru.ssau.pokubuy.web.Endpoint;
import ru.ssau.pokubuy.web.Matching;

public abstract class EpMatching implements Endpoint {
    private final Matching matching;

    protected EpMatching(Matching matching) {
        this.matching = matching;
    }

    @Override
    public boolean matches(String method, String path) {
        return this.matching.matches(method, path);
    }
}
