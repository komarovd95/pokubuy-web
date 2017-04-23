package common;

public abstract class MatchedEp<R> implements Endpoint<R> {
    private final Matching matching;

    protected MatchedEp(Matching matching) {
        this.matching = matching;
    }

    @Override
    public boolean matches(Request request) {
        return this.matching.matches(request.path());
    }
}
