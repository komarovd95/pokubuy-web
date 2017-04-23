package last;

import java.util.function.Function;

public final class EtRoute implements Entry {
    private final Matching matching;
    private final Function<? super Request, ? extends Render> renderFactory;

    public EtRoute(final Matching matching, final Render render) {
        this(matching, req -> render);
    }

    public EtRoute(final Matching matching,
                   final Function<? super Request, ? extends Render> renderFactory) {
        this.matching = matching;
        this.renderFactory = renderFactory;
    }

    @Override
    public boolean matches(final Request request) {
        return new Request.Smart(request).matches(this.matching);
    }

    @Override
    public Render render(final Request request) {
        return this.renderFactory.apply(request);
    }
}
