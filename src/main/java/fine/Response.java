package fine;

import java.io.IOException;
import java.io.InputStream;

public interface Response {
    int status();
    Iterable<Header> headers();
    InputStream body() throws IOException;
}
