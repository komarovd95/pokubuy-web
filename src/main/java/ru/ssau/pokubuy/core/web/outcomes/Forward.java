package ru.ssau.pokubuy.core.web.outcomes;

import ru.ssau.pokubuy.core.web.Outcome;
import ru.ssau.pokubuy.core.web.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class Forward implements Outcome {
    private final RequestMapping<RequestDispatcher> requestMapping;

    public Forward(RequestDispatcher dispatcher) {
        this(r -> dispatcher);
    }

    public Forward(RequestMapping<RequestDispatcher> requestMapping) {
        this.requestMapping = requestMapping;
    }

    @Override
    public void apply(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.requestMapping.map(request).forward(request, response);
    }
}
