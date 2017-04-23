package ru.ssau.pokubuy.web.matchings;

import ru.ssau.pokubuy.web.Matching;

import java.util.Arrays;
import java.util.Iterator;

public final class MtAnd implements Matching {
    private final Iterable<Matching> matchings;

    public MtAnd(Matching... matchings) {
        this(Arrays.asList(matchings));
    }

    public MtAnd(Iterable<Matching> matchings) {
        this.matchings = matchings;
    }

    @Override
    public boolean matches(String method, String path) {
        boolean result = true;

        Iterator<Matching> iterator = this.matchings.iterator();
        while (iterator.hasNext() && result) {
            Matching matching = iterator.next();
            result = matching.matches(method, path);
        }

        return result;
    }
}
