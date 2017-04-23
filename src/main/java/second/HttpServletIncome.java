package second;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

public final class HttpServletIncome implements Income {
    private final transient HttpServletRequest request;
    private final transient HttpServletResponse response;

    public HttpServletIncome(final HttpServletRequest request, final HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public Request request() {
        return Collections::emptyList;
    }

    @Override
    public Outcome asset(final Response<?> response) {
        return () -> {
            for (String header : response.headers()) {
                HttpServletIncome.this.response.setHeader(header, header);
            }

            try {
                HttpServletIncome.this.request.getServletContext().getNamedDispatcher("default").forward(
                        HttpServletIncome.this.request,
                        HttpServletIncome.this.response
                );
            } catch (ServletException e) {
                throw new IOException(e);
            }
        };
    }

    @Override
    public Outcome render(final Response<? extends View> response) {
        return new HttpServletOutcome(this.request, this.response) {
            @Override
            public void send() throws IOException {
                this.attachHeaders(response.headers());

                View view = response.body();

                this.attachAttributes(view.attributes());

                this.forward(this.dispatcher(view.name()));
            }
        };
    }

    @Override
    public Outcome out(final Response<?> response) {
        return new HttpServletOutcome(this.request, this.response) {
            @Override
            public void send() throws IOException {
                this.attachHeaders(response.headers());

                Object body = response.body();


            }
        };
    }
}
