package fine;

import java.time.Instant;

public final class RsCookieAuth extends RsWrap {
    public RsCookieAuth(final Response origin, final String cookieName, final Principal principal) {
        super(
            new RsWithCookie(
                origin,
                new Cookie(
                    cookieName,
                    principal.identity(),
                    Instant.now().plusSeconds(3600)
                )
            )
        );
    }
}
