package common;

import java.util.Optional;

public final class HttpPathOnlyRq implements Request {
    private final transient Request origin;

    public HttpPathOnlyRq(Request origin) {
        this.origin = origin;
    }

    @Override
    public String path() {
        return this.originPath().orElse("");
    }

    private Optional<String> originPath() {
        String[] methodAndUrl = this.methodAndUrl();

        if (methodAndUrl.length >= 2) {
            return Optional.of(methodAndUrl[1]);
        } else {
            return Optional.empty();
        }
    }

    private String[] methodAndUrl() {
        return this.origin.path().split(" ");
    }
}
