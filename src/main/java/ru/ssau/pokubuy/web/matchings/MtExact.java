package ru.ssau.pokubuy.web.matchings;

public final class MtExact implements PathMatching {
    private final String exact;

    public MtExact(String exact) {
        this.exact = exact;
    }

    @Override
    public boolean matches(String path) {
        return this.exact.equalsIgnoreCase(path);
    }
}
