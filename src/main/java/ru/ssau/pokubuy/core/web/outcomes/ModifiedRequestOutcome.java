package ru.ssau.pokubuy.core.web.outcomes;

import ru.ssau.pokubuy.core.web.Outcome;
import ru.ssau.pokubuy.core.web.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class ModifiedRequestOutcome implements Outcome {
    private final Outcome origin;
    private final RequestMapping<? extends HttpServletRequest> requestMapping;

    public ModifiedRequestOutcome(Outcome origin,
                                  RequestMapping<? extends HttpServletRequest> requestMapping) {
        this.origin = origin;
        this.requestMapping = requestMapping;
    }

    @Override
    public void apply(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.origin.apply(this.requestMapping.map(request), response);
    }
}
