package common;

public final class IndexEp extends MatchedEp<Response> {
    private final Endpoint<? extends Response> origin;

    public IndexEp(Endpoint<? extends Response> origin) {
        super(new ExactMt("/"));

        this.origin = origin;
    }

    @Override
    public boolean matches(Request request) {
        return super.matches(request) && this.origin.matches(request);
    }

    @Override
    public Response act(Request request) {
        return this.origin.act(request);
    }
}
