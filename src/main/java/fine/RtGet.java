package fine;

public final class RtGet extends RtMatched {
    public RtGet(final Route origin) {
        super(origin, new MtMethod("GET"));
    }
}
