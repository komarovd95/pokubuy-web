package last;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.Supplier;

public final class RqHasModified implements Supplier<Boolean> {
    private final transient Request request;
    private final String headerName;
    private final Function<? super Request, ? extends LocalDateTime> lastModified;

    public RqHasModified(final Request request, final LocalDateTime lastModified) {
        this(request, req -> lastModified);
    }

    public RqHasModified(final Request request, final String headerName,
                         final LocalDateTime lastModified) {
        this(request, headerName, req -> lastModified);
    }

    public RqHasModified(final Request request,
                         final Function<? super Request, ? extends LocalDateTime> lastModified) {
        this(request, "If-Modified-Since", lastModified);
    }

    public RqHasModified(final Request request, final String headerName,
                         final Function<? super Request, ? extends LocalDateTime> lastModified) {
        this.request = request;
        this.headerName = headerName;
        this.lastModified = lastModified;
    }

    @Override
    public Boolean get() {
        LocalDateTime ifModified;
        try {
            Header header = request.headers().header(this.headerName);
            ifModified = LocalDateTime.parse(header.value(), DateTimeFormatter.BASIC_ISO_DATE);
        } catch (NoSuchElementException ignored) {
            ifModified = LocalDateTime.MIN;
        }

        LocalDateTime lastModified = this.lastModified.apply(request);

        return ifModified.isBefore(lastModified);
    }
}
