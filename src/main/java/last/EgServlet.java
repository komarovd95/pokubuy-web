package last;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.function.BiFunction;

public class EgServlet implements Engine {
    private final transient HttpServletRequest request;
    private final transient HttpServletResponse response;
    private final String defaultServletName;

    public EgServlet(final HttpServletRequest request, final HttpServletResponse response) {
        this(request, response, "default");
    }

    public EgServlet(final HttpServletRequest request, final HttpServletResponse response,
                     final String defaultServletName) {
        this.request = request;
        this.response = response;
        this.defaultServletName = defaultServletName;
    }

    @Override
    public void page(String path, Iterable<Header> headers, Iterable<String> attributes)
            throws IOException {
        this.attachHeaders(headers);
        this.attachAttributes(attributes);

        try {
            this.request.getRequestDispatcher(path)
                    .forward(this.request, this.response);
        } catch (ServletException e) {
            throw new IOException(e);
        }
    }

    @Override
    public void asset(int status, Iterable<Header> headers) throws IOException {
        this.setStatus(status);
        this.attachHeaders(headers);

        try {
            this.request.getServletContext().getNamedDispatcher(this.defaultServletName)
                    .forward(this.request, response);
        } catch (ServletException e) {
            throw new IOException(e);
        }
    }

    @Override
    public void respond(int status, Iterable<Header> headers, CharSequence body)
            throws IOException {
        this.setStatus(status);
        this.attachHeaders(headers);

        try (PrintWriter writer = this.response.getWriter()) {
            writer.append(body);
        }
    }

    private void attachHeaders(final Iterable<Header> headers) {
        for (Header header : headers) {
            this.response.setHeader(header.name(), header.value());
        }
    }

    private void attachAttributes(final Iterable<String> attributes) {
        for (String attribute : attributes) {
            this.request.setAttribute(attribute, attribute);
        }
    }

    private void setStatus(final int status) {
        this.response.setStatus(status);
    }

    public static final class Factory implements
            BiFunction<HttpServletRequest, HttpServletResponse, Engine> {
        @Override
        public Engine apply(HttpServletRequest request, HttpServletResponse response) {
            return new EgServlet(request, response);
        }
    }
}
