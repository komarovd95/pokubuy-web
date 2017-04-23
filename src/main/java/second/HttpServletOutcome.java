package second;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class HttpServletOutcome implements Outcome {
    private final transient HttpServletRequest request;
    private final transient HttpServletResponse response;

    protected HttpServletOutcome(final HttpServletRequest request,
                                 final HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    protected void attachHeaders(final Iterable<String> headers) {
        for (String header : headers) {
            this.response.setHeader(header, header);
        }
    }

    protected void attachAttributes(final Iterable<String> attributes) {
        for (String attribute : attributes) {
            this.request.setAttribute(attribute, attribute);
        }
    }

    protected RequestDispatcher dispatcher(final String path) {
        return this.request.getRequestDispatcher(path);
    }

    protected RequestDispatcher namedDispatcher(final String name) {
        return this.request.getServletContext().getNamedDispatcher(name);
    }

    protected void forward(final RequestDispatcher dispatcher) throws IOException {
        try {
            dispatcher.forward(this.request, this.response);
        } catch (ServletException e) {
            throw new IOException(e);
        }
    }
}
