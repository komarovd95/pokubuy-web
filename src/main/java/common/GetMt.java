package common;

public final class GetMt implements Matching {
    @Override
    public boolean matches(String url) {
        return url.startsWith("GET");
    }
}
