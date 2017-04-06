package ru.ssau.pokubuy.core.web.misc;

import ru.ssau.pokubuy.core.web.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

public final class RequestDispatch implements RequestMapping<RequestDispatcher> {
    private final String uri;

    public RequestDispatch(String uri) {
        this.uri = uri;
    }

    @Override
    public RequestDispatcher map(HttpServletRequest request) {
        return request.getRequestDispatcher(this.uri);
    }
}
