package fine;

import java.io.IOException;
import java.io.InputStream;

public interface Request {
    String method();
    String url();
    Iterable<Header> headers();
    InputStream body() throws IOException;
}
