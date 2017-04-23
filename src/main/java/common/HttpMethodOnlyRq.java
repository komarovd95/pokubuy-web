package common;

import java.util.Optional;

public final class HttpMethodOnlyRq implements Request {
    private final transient Request origin;

    public HttpMethodOnlyRq(Request origin) {
        this.origin = origin;
    }

    @Override
    public String path() {
        return this.originMethod().orElse("");
    }

    private Optional<String> originMethod() {
        String[] methodAndUrl = this.methodAndUrl();

        if (methodAndUrl.length == 2) {
            return Optional.of(methodAndUrl[0]);
        } else {
            return Optional.empty();
        }
    }

    private String[] methodAndUrl() {
        return this.origin.path().split(" ", 2);
    }
}
