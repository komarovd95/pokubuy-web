package fine;

public final class RsWithStatus extends RsWrap {
    private final int status;

    public RsWithStatus(final Response origin, final int status) {
        super(origin);

        this.status = status;
    }

    @Override
    public int status() {
        return this.status;
    }
}
