package last;

public final class MtExact implements Matching {
    private final String path;

    public MtExact(final String path) {
        this.path = path;
    }

    @Override
    public boolean matches(String method, String url) {
        return this.path.equalsIgnoreCase(url);
    }
}
