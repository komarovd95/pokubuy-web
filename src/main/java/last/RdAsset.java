package last;

import java.io.IOException;

public final class RdAsset implements Render {
    private final transient Response response;

    public RdAsset() {
        this(new RsEmpty());
    }

    public RdAsset(final Response response) {
        this.response = response;
    }

    @Override
    public void render(final Engine engine) throws IOException {
        engine.asset(
            this.response.status(),
            this.response.headers()
        );
    }
}
