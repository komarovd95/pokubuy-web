package ru.ssau.pokubuy.core.web.requests;

import ru.ssau.pokubuy.core.web.Request;

import javax.servlet.http.HttpServletRequest;

public final class HttpRequest implements Request {
    private final transient HttpServletRequest origin;

    public HttpRequest(HttpServletRequest origin) {
        this.origin = origin;
    }
}
