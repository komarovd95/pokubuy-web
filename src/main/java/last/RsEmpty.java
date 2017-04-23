package last;

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
    public CharSequence body() {
        return "";
    }
}
