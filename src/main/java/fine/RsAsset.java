package fine;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Optional;

public final class RsAsset implements Response {
    private final static String FORWARD_HEADER = "X-Forwarded-Asset";

    private final String assetPipe;

    public RsAsset() {
        this("default");
    }

    public RsAsset(final String assetPipe) {
        this.assetPipe = assetPipe;
    }

    @Override
    public int status() {
        return 200;
    }

    @Override
    public Iterable<Header> headers() {
        return Collections.singleton(
            new HdSimple(RsAsset.FORWARD_HEADER, this.assetPipe)
        );
    }

    @Override
    public InputStream body() throws IOException {
        return new ByteArrayInputStream(new byte[0]);
    }

    public static final class Smart {
        private final transient Response origin;

        public Smart(final Response origin) {
            this.origin = origin;
        }

        public Optional<String> hasAsset() {
            for (Header header : this.origin.headers()) {
                if (RsAsset.FORWARD_HEADER.equalsIgnoreCase(header.name())) {
                    return Optional.of(header.value());
                }
            }

            return Optional.empty();
        }
    }
}
