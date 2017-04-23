package fine;

public final class RqCookieAuth extends RqWrap implements RqWithAuth {
    private final String authCookieName;

    public RqCookieAuth(final Request origin, final String authCookieName) {
        super(origin);

        this.authCookieName = authCookieName;
    }

    @Override
    public String identity() {
        return new RqCookies(this.origin).cookie(this.authCookieName)
                .orElse(Principal.ANONYMOUS);
    }
}
