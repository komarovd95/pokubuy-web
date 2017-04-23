package ru.ssau.pokubuy.web.responses;

import ru.ssau.pokubuy.web.Response;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class RsLastModified implements Response {
    private final transient Response origin;

    public RsLastModified(Response origin) {
        this.origin = origin;
    }

    @Override
    public void send(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
