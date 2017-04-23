package fine;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

public final class RsEmpty implements Response {
    @Override
    public int status() {
        return 200;
    }

    @Override
    public Iterable<Header> headers() {
        return Collections.emptyList();
    }

    @Override
    public InputStream body() throws IOException {
        return new ByteArrayInputStream(new byte[0]);
    }
}
