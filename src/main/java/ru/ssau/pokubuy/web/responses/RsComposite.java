package ru.ssau.pokubuy.web.responses;

import ru.ssau.pokubuy.web.Response;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public final class RsComposite implements Response {
    private final transient Iterable<Response> responses;

    public RsComposite(Response... responses) {
        this(Arrays.asList(responses));
    }

    public RsComposite(Iterable<Response> responses) {
        this.responses = responses;
    }

    @Override
    public void send(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        for (Response res : this.responses) {
            res.send(request, response);
        }
    }
}
