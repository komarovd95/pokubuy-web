package last;

import java.util.Iterator;

public final class AbsentHeaders implements Headers {
    @Override
    public Header header(String name) {
        return null;
    }

    @Override
    public boolean contains(String name) {
        return false;
    }

    @Override
    public Iterator<Header> iterator() {
        return null;
    }
}
