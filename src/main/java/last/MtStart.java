package last;

public final class MtStart implements Matching {
    private final String prefix;

    public MtStart(final String prefix) {
        this.prefix = prefix;
    }

    @Override
    public boolean matches(String method, String url) {
        return url.startsWith(this.prefix);
    }
}
