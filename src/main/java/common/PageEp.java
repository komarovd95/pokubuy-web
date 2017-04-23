package common;

public final class PageEp extends MatchedEp<Response> {
    private final String page;

    public PageEp(String page) {
        this(page, new GetMt());
    }

    public PageEp(String page, Matching matching) {
        super(matching);

        this.page = page;
    }

    @Override
    public Response act(Request request) {
        return new ForwardRs(this.page);
    }
}
