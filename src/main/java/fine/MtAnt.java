package fine;

import java.io.IOException;

public final class MtAnt implements Matching {
    private final String pattern;

    public MtAnt(final String pattern) {
        this.pattern = pattern;
    }

    @Override
    public boolean matches(final Request request) throws IOException {
        return new AntPathMatcher().match(this.pattern, request.url());
    }
}
