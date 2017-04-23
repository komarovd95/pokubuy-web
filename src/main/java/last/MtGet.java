package last;

public final class MtGet implements Matching {
    private final Matching origin;

    public MtGet(final Matching origin) {
        this.origin = origin;
    }

    @Override
    public boolean matches(String method, String url) {
        return "GET".equalsIgnoreCase(method) && this.origin.matches(method, url);
    }
}
