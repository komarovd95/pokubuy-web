package last;

public final class MtPost implements Matching {
    private final Matching origin;

    public MtPost(final Matching origin) {
        this.origin = origin;
    }

    @Override
    public boolean matches(String method, String url) {
        return "POST".equalsIgnoreCase(method) && this.origin.matches(method, url);
    }
}
