package last;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.function.BiFunction;

public final class FtServlet implements Front {
    private final BiFunction<HttpServletRequest, HttpServletResponse, Engine> engineFactory;
    private final BiFunction<HttpServletRequest, HttpServletResponse, Request> requestFactory;
    private final Iterable<Entry> entries;

    public FtServlet(final BiFunction<HttpServletRequest, HttpServletResponse, Engine> engineFactory,
                     final BiFunction<HttpServletRequest, HttpServletResponse, Request> requestFactory,
                     final Entry... entries) {
        this(engineFactory, requestFactory, Arrays.asList(entries));
    }

    public FtServlet(final BiFunction<HttpServletRequest, HttpServletResponse, Engine> engineFactory,
                     final BiFunction<HttpServletRequest, HttpServletResponse, Request> requestFactory,
                     final Iterable<Entry> entries) {
        this.engineFactory = engineFactory;
        this.requestFactory = requestFactory;
        this.entries = entries;
    }

    @Override
    public void act(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Engine engine = this.engineFactory.apply(request, response);

        Request rq = this.requestFactory.apply(request, response);

        Render render = this.findRender(rq);

        render.render(engine);
    }

    private Render findRender(final Request rq) {
        for (Entry entry : this.entries) {
            if (entry.matches(rq)) {
                return entry.render(rq);
            }
        }

        throw new IllegalStateException("No fallback entry. Matching failing");
    }
}
