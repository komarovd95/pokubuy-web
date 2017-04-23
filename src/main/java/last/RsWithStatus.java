package last;

public final class RsWithStatus implements Response {
    private final transient Response origin;
    private final int status;

    public RsWithStatus(final Response origin, final int status) {
        this.origin = origin;
        this.status = status;
    }

    @Override
    public int status() {
        return this.status;
    }

    @Override
    public Iterable<Header> headers() {
        return this.origin.headers();
    }

    @Override
    public CharSequence body() {
        return this.origin.body();
    }
}
