package fine;

import java.util.Optional;

public final class RqCookies extends RqWrap {
    public RqCookies(final Request origin) {
        super(origin);
    }

    public Optional<String> cookie(final String cookieName) {
        Optional<Header> cookieHeader = new RqHeaders(this.origin).header("Cookie");
        if (cookieHeader.isPresent()) {
            String[] cookies = cookieHeader.get().value().split(";\\s?");

            for (String cookie : cookies) {
                String[] nameAndValue = cookie.split("=", 2);
                if (nameAndValue[0].equalsIgnoreCase(cookieName)) {
                    return Optional.of(nameAndValue[1]);
                }
            }
        }

        return Optional.empty();
    }
}
