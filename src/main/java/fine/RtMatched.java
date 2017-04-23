package fine;

import java.io.IOException;
import java.util.Optional;

public abstract class RtMatched extends RtWrap {
    private final Matching matching;

    protected RtMatched(final Route origin, final Matching matching) {
        super(origin);

        this.matching = matching;
    }

    @Override
    public Optional<Response> enter(final Request request) throws IOException {
        if (this.matching.matches(request)) {
            return super.enter(request);
        }

        return Optional.empty();
    }
}
