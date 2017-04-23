package fine;

public final class RsNotFound extends RsWrap {
    public RsNotFound() {
        this(new RsEmpty());
    }

    public RsNotFound(final Response origin) {
        super(origin);
    }

    @Override
    public int status() {
        return 404;
    }
}
