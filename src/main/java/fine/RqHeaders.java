package fine;

import java.util.Optional;

public final class RqHeaders extends RqWrap {
    public RqHeaders(final Request origin) {
        super(origin);
    }

    public Optional<Header> header(final String headerName) {
        for (Header header : this.headers()) {
            if (headerName.equalsIgnoreCase(header.name())) {
                return Optional.of(header);
            }
        }

        return Optional.empty();
    }
}
