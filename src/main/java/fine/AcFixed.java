package fine;

import java.io.IOException;

public final class AcFixed extends AcWrap {
    public AcFixed(final Response response) {
        super(new Action() {
            @Override
            public Response act(Request request) throws IOException {
                return response;
            }
        });
    }
}
