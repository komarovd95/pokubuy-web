package last;

import java.io.IOException;
import java.util.Arrays;

public final class RdJsp implements Render {
    private final transient Response response;
    private final String path;
    private final Iterable<String> attributes;

    public RdJsp(final String path, final String... attributes) {
        this(path, Arrays.asList(attributes));
    }

    public RdJsp(final String path, final Iterable<String> attributes) {
        this(new RsEmpty(), path, attributes);
    }

    public RdJsp(final Response response, final String path, final Iterable<String> attributes) {
        this.response = response;
        this.path = path;
        this.attributes = attributes;
    }

    @Override
    public void render(final Engine engine) throws IOException {
        engine.page(
            this.path,
            this.response.headers(),
            this.attributes
        );
    }
}
