package fine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class RsWithHeaders extends RsWrap {
    private final Iterable<Header> headers;

    public RsWithHeaders(final Response origin, final Header... headers) {
        this(origin, Arrays.asList(headers));
    }

    public RsWithHeaders(final Response origin, final Iterable<Header> headers) {
        super(origin);

        this.headers = headers;
    }

    @Override
    public Iterable<Header> headers() {
        List<Header> headers = new ArrayList<>();
        for (Header header : super.headers()) {
            headers.add(header);
        }

        for (Header header : this.headers) {
            headers.add(header);
        }

        return headers;
    }
}
