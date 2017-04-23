package fine;

import java.io.IOException;

public final class MtStart implements Matching {
    private final String startsWith;

    public MtStart(final String startsWith) {
        this.startsWith = startsWith;
    }

    @Override
    public boolean matches(final Request request) throws IOException {
        return request.url().toUpperCase().startsWith(this.startsWith.toUpperCase());
    }
}
