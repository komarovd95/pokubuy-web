package last;

import java.io.IOException;

public final class RdBody implements Render {
    private final transient Response response;

    public RdBody(final Response response) {
        this.response = response;
    }

    @Override
    public void render(final Engine engine) throws IOException {
        engine.respond(
            this.response.status(),
            this.response.headers(),
            this.response.body()
        );
    }
}
