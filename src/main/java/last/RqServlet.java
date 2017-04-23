package last;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.function.BiFunction;

public final class RqServlet implements Request {
    private final transient HttpServletRequest request;

    public RqServlet(final HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String method() {
        return this.request.getMethod();
    }

    @Override
    public String url() {
        return this.request.getServletPath();
    }

    @Override
    public Headers headers() {
        List<Header> headers = new ArrayList<>();
        Enumeration<String> headerNames = this.request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();

            headers.add(new HdSimple(headerName, this.request.getHeader(headerName)));
        }

        return new SimpleHeaders(headers);
    }

    @Override
    public CharSequence body() {
        return "body"; // TODO
    }

    public static final class Factory implements
            BiFunction<HttpServletRequest, HttpServletResponse, Request> {
        @Override
        public Request apply(HttpServletRequest request, HttpServletResponse response) {
            return new RqServlet(request);
        }
    }
}
