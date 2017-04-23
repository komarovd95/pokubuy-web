package fine;

public final class RsWithCookie extends RsWrap {
    public RsWithCookie(final Response origin, final Cookie cookie) {
        super(new RsWithHeaders(origin, new HdSimple("Set-Cookie", cookie.toString())));
    }
}
