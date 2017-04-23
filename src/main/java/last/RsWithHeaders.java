package last;

import java.util.Arrays;

public final class RsWithHeaders implements Response {
    private final transient Response origin;
    private final Iterable<Header> headers;

    public RsWithHeaders(final Response origin, final Header... headers) {
        this(origin, Arrays.asList(headers));
    }

    public RsWithHeaders(final Response origin, final Iterable<Header> headers) {
        this.origin = origin;
        this.headers = headers;
    }

    @Override
    public int status() {
        return this.origin.status();
    }

    @Override
    public Iterable<Header> headers() {
        return this.headers;
    }

    @Override
    public CharSequence body() {
        return this.origin.body();
    }
}
