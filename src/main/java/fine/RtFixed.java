package fine;

import java.util.Optional;

public final class RtFixed extends RtMatched {
    public RtFixed(final String fixed, final Action action) {
        super((req) -> Optional.of(action.act(req)), new MtFixed(fixed));
    }
}
