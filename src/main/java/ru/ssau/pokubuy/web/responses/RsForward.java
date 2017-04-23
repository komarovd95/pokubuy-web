package ru.ssau.pokubuy.web.responses;

import ru.ssau.pokubuy.web.Response;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class RsForward implements Response {
    private final RequestDispatcher dispatcher;

    public RsForward(RequestDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public void send(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String servletPath = request.getServletPath();
        String realPath = request.getServletContext().getRealPath("/css/app.css");
        this.dispatcher.forward(request, response);
    }
}
