package fine;

import java.util.Optional;
import java.util.regex.Pattern;

public final class RtRegex extends RtMatched {
    public RtRegex(final String pattern, final Action action) {
        this(Pattern.compile(pattern), action);
    }

    public RtRegex(final Pattern pattern, final Action action) {
        super(request -> Optional.of(action.act(request)), new MtRegex(pattern));
    }
}
