package last;

public final class EtFork implements Entry {
    private final Matching matching;
    private final Render onGet;
    private final Render onPost;

    public EtFork(final Matching matching, final Render onGet, final Render onPost) {
        this.matching = new MtOr(
            new MtGet(matching),
            new MtPost(matching)
        );
        this.onGet = onGet;
        this.onPost = onPost;
    }

    @Override
    public boolean matches(Request request) {
        return new Request.Smart(request).matches(this.matching);
    }

    @Override
    public Render render(Request request) {
        Request.Smart smart = new Request.Smart(request);

        if (smart.matches(new MtGet(this.matching))) {
            return this.onGet;
        } else {
            return this.onPost;
        }
    }
}
