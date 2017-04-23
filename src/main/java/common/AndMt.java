package common;

import java.util.Arrays;
import java.util.Iterator;

public final class AndMt implements Matching {
    private final Iterable<Matching> matchings;

    public AndMt(Matching... matchings) {
        this(Arrays.asList(matchings));
    }

    public AndMt(Iterable<Matching> matchings) {
        this.matchings = matchings;
    }

    @Override
    public boolean matches(String url) {
        boolean result = true;

        Iterator<Matching> iterator = this.matchings.iterator();
        while (iterator.hasNext() && result) {
            Matching matching = iterator.next();
            result = matching.matches(url);
        }

        return result;
    }
}
