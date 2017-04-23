package last;

public final class RsLastModified implements Response {
    private final transient Response origin;
    private final long lastModified;

    public RsLastModified(final Response origin, final long lastModified) {
        this.origin = origin;
        this.lastModified = lastModified;
    }

    @Override
    public int status() {
        return 0;
    }

    @Override
    public Headers headers() {
        return null;
    }

    @Override
    public CharSequence body() {
        return null;
    }
}
