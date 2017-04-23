package fine;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public final class MtFixed implements Matching {
    private final String fixed;

    public MtFixed(final String fixed) {
        this.fixed = fixed;
    }

    @Override
    public boolean matches(final Request request) throws IOException {
        try {
            URI requestedUri = new URI(request.url());

            return this.fixed.equalsIgnoreCase(requestedUri.getPath());
        } catch (URISyntaxException e) {
            return false;
        }
    }
}
