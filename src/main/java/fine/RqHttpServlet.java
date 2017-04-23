package fine;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public final class RqHttpServlet implements Request {
    private final transient HttpServletRequest origin;

    public RqHttpServlet(final HttpServletRequest origin) {
        this.origin = origin;
    }

    @Override
    public String method() {
        return this.origin.getMethod();
    }

    @Override
    public String url() {
        if (this.origin.getQueryString() != null) {
            return this.origin.getRequestURI() + "?" + this.origin.getQueryString();
        }

        return this.origin.getRequestURI();
    }

    @Override
    public Iterable<Header> headers() {
        List<Header> headers = new ArrayList<>();
        Enumeration<String> headerNames = this.origin.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = this.origin.getHeader(headerName);

            headers.add(new HdSimple(headerName, headerValue));
        }

        return headers;
    }

    @Override
    public InputStream body() throws IOException {
        return this.origin.getInputStream();
    }
}
