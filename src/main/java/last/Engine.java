package last;

import java.io.IOException;

public interface Engine {
    void page(String path, Iterable<Header> headers, Iterable<String> attributes) throws IOException;
    void asset(int status, Iterable<Header> headers) throws IOException;
    void respond(int status, Iterable<Header> headers, CharSequence body) throws IOException;
}
