package fine;

import java.io.IOException;
import java.io.InputStream;

public abstract class RsWrap implements Response {
    protected final Response origin;

    protected RsWrap(final Response origin) {
        this.origin = origin;
    }

    @Override
    public int status() {
        return this.origin.status();
    }

    @Override
    public Iterable<Header> headers() {
        return this.origin.headers();
    }

    @Override
    public InputStream body() throws IOException {
        return this.origin.body();
    }
}
