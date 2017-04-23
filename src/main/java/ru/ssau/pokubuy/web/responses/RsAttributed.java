package ru.ssau.pokubuy.web.responses;

import ru.ssau.pokubuy.web.Response;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class RsAttributed implements Response {
    private final Iterable<?> attributes;

    public RsAttributed(Iterable<?> attributes) {
        this.attributes = attributes;
    }

    @Override
    public void send(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        for (Object attribute : this.attributes) {
            request.setAttribute(null, attribute);
        }
    }
}
