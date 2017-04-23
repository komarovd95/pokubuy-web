package ru.ssau.pokubuy.web.matchings;

public final class MtExtension implements PathMatching {
    private final String extension;

    public MtExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public boolean matches(String path) {
        return new MtPattern(this.generatePattern()).matches(path);
    }

    private String generatePattern() {
        return ".+\\." + this.extension + "$";
    }
}
