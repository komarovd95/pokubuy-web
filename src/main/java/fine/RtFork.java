package fine;

import java.io.IOException;
import java.util.Optional;

public final class RtFork extends RtMatched implements Route {
    public RtFork(final String fixed, final Action onGet, final Action onPost) {
        super(new Route() {
            @Override
            public Optional<Response> enter(Request request) throws IOException {
                Optional<Response> get = new RtGet(new RtFixed(fixed, onGet)).enter(request);
                if (get.isPresent()) {
                    return get;
                } else {
                    Optional<Response> post = new RtPost(new RtFixed(fixed, onPost)).enter(request);

                    if (post.isPresent()) {
                        return post;
                    }

                    return Optional.empty();
                }

            }
        }, new MtFixed(fixed));
    }
}
