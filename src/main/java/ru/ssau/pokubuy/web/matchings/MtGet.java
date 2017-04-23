package ru.ssau.pokubuy.web.matchings;

import ru.ssau.pokubuy.web.Matching;

public final class MtGet implements Matching {
    private final Matching origin;

    public MtGet(Matching origin) {
        this.origin = origin;
    }

    @Override
    public boolean matches(String method, String path) {
        return method.equalsIgnoreCase("GET")
                && this.origin.matches(method, path);
    }
}
