package common;

import javax.servlet.http.HttpServletRequest;

public final class HttpServletRq implements Request {
    private final transient HttpServletRequest origin;

    public HttpServletRq(HttpServletRequest origin) {
        this.origin = origin;
    }

    @Override
    public String path() {
        return this.origin.getMethod() + " " + this.origin.getServletPath();
    }
}
