package fine;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public final class MtEnd implements Matching {
    private final String endsWith;

    public MtEnd(final String endsWith) {
        this.endsWith = endsWith;
    }

    @Override
    public boolean matches(final Request request) throws IOException {
        try {
            URI requestedUri = new URI(request.url());

            String path = requestedUri.getPath();

            return path.toUpperCase().endsWith(this.endsWith.toUpperCase());
        } catch (URISyntaxException e) {
            return false;
        }
    }
}
