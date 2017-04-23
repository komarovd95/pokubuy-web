package fine;

import java.io.IOException;
import java.io.InputStream;

public class RqWrap implements Request {
    protected final transient Request origin;

    public RqWrap(final Request origin) {
        this.origin = origin;
    }

    @Override
    public String method() {
        return this.origin.method();
    }

    @Override
    public String url() {
        return this.origin.url();
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
