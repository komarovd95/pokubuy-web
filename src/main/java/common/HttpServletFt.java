package common;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public final class HttpServletFt implements Front<HttpServletRequest, Response> {
    private final Iterable<Endpoint<? extends Response>> endpoints;

    public HttpServletFt(Endpoint<? extends Response>... endpoints) {
        this(Arrays.asList(endpoints));
    }

    public HttpServletFt(Iterable<Endpoint<? extends Response>> endpoints) {
        this.endpoints = endpoints;
    }

    @Override
    public Request request(HttpServletRequest request) {
        return new HttpServletRq(request);
    }

    @Override
    public Response response(Request request) {
        for (Endpoint<? extends Response> endpoint : this.endpoints) {
            if (endpoint.matches(request)) {
                return endpoint.act(request);
            }
        }

        return new EmptyRs();
    }
}
