package last;

import java.util.Arrays;

public final class MtOr implements Matching {
    private final Iterable<Matching> matchings;

    public MtOr(final Matching... matchings) {
        this(Arrays.asList(matchings));
    }

    public MtOr(final Iterable<Matching> matchings) {
        this.matchings = matchings;
    }

    @Override
    public boolean matches(String method, String url) {
        for (Matching matching : this.matchings) {
            if (matching.matches(method, url)) {
                return true;
            }
        }

        return false;
    }
}
