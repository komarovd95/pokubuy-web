package ru.ssau.pokubuy.web.matchings;

import ru.ssau.pokubuy.web.Matching;

public final class MtPost implements Matching {
    private final Matching origin;

    public MtPost(Matching origin) {
        this.origin = origin;
    }

    @Override
    public boolean matches(String method, String path) {
        return method.equalsIgnoreCase("POST")
                && this.origin.matches(method, path);
    }
}
