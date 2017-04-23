package fine;

import java.io.IOException;
import java.time.Instant;
import java.util.Optional;

public final class CookieAuth implements Auth {
    private final Codec<String, Principal> codec;
    private final String authCookieName;
    private final int expiresIn;

    public CookieAuth(final Codec<String, Principal> codec, final String authCookieName,
                      final int expiresIn) {
        this.codec = codec;
        this.authCookieName = authCookieName;
        this.expiresIn = expiresIn;
    }

    @Override
    public Optional<Principal> authenticated(final Request request) throws IOException {
        return new RqCookies(request).cookie(this.authCookieName)
                .flatMap(this.codec::decode);
    }

    @Override
    public Response authenticate(final Response origin, final Principal principal)
            throws IOException, AuthException {
        try {
            return new RsWithCookie(
                origin,
                new Cookie(
                    this.authCookieName,
                    this.codec.encode(principal),
                    Instant.now().plusSeconds(this.expiresIn)
                )
            );
        } catch (EncodeException e) {
            throw new AuthException(e.getLocalizedMessage());
        }
    }

    @Override
    public Response leave(final Response origin) throws IOException {
        return new RsWithCookie(
            origin,
            new Cookie(
                this.authCookieName,
                Principal.ANONYMOUS,
                Instant.ofEpochSecond(0L)
            )
        );
    }
}
