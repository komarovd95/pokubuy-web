package ru.ssau.pokubuy.web.matchings;

import java.util.regex.Pattern;

public final class MtPattern implements PathMatching {
    private final Pattern pattern;

    public MtPattern(String pattern) {
        this(Pattern.compile(pattern));
    }

    public MtPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public boolean matches(String path) {
        return this.pattern.matcher(path).matches();
    }
}
