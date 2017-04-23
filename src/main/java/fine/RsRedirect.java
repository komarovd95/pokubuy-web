package fine;

import java.net.URI;
import java.net.URISyntaxException;

public final class RsRedirect extends RsWrap {
    public RsRedirect() {
        this("http://localhost:8080");
    }

    public RsRedirect(final String to) {
        super(RsRedirect.make(new HdSimple("Location", to)));
    }

    public static final class Query extends RsWrap {
        public Query(final Request request) {
            super(RsRedirect.make(
                new HdLazy(
                    "Location",
                    () -> {
                        try {
                            QueryString qs = new QueryString(new URI(request.url()));
                            return qs.single("redirect")
                                    .orElse("http://localhost:8080");
                        } catch (URISyntaxException e) {
                            return "/";
                        }
                    }
                )
            ));
        }
    }

    private static Response make(final Header locationHeader) {
        return new RsWithStatus(
            new RsWithHeaders(
                new RsEmpty(),
                locationHeader
            ), 302
        );
    }
}
