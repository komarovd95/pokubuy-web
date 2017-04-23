package ru.ssau.pokubuy.web.requests;

import ru.ssau.pokubuy.web.Matching;
import ru.ssau.pokubuy.web.Request;
import ru.ssau.pokubuy.web.Response;
import ru.ssau.pokubuy.web.responses.RsForward;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

public class RqHttp implements Request {
    private final transient HttpServletRequest request;

    public RqHttp(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public Response forward(String path) {
        return new RsForward(
            this.request.getRequestDispatcher(path)
        );
    }

    @Override
    public Response serve() {
        return new RsForward(
            this.retrieveDefaultDispatcher()
        );
    }

    @Override
    public String header(String name) {
        return this.request.getHeader(name);
    }

    private RequestDispatcher retrieveDefaultDispatcher() {
        return this.request.getServletContext().getNamedDispatcher("default");
    }

    @Override
    public boolean matches(Matching matching) {
        return matching.matches(
                request.getMethod(),
                request.getServletPath()
        );
    }
}
