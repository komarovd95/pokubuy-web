package ru.ssau.pokubuy.web.matchings;

import ru.ssau.pokubuy.web.Matching;

@FunctionalInterface
public interface PathMatching extends Matching {
    boolean matches(String path);

    default boolean matches(String method, String path) {
        return this.matches(path);
    }
}
