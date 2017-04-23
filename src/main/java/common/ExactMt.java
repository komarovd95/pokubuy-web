package common;

public final class ExactMt implements Matching {
    private final String exact;

    public ExactMt(String exact) {
        this.exact = exact;
    }

    @Override
    public boolean matches(String url) {
        return this.exact.equalsIgnoreCase(url);
    }
}
