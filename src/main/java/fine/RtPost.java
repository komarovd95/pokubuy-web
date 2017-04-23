package fine;

public final class RtPost extends RtMatched {
    public RtPost(final Route origin) {
        super(origin, new MtMethod("POST"));
    }
}
