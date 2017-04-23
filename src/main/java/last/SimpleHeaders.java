package last;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class SimpleHeaders implements Headers {
    private final Iterable<Header> origin;

    public SimpleHeaders(final Iterable<Header> origin) {
        this.origin = origin;
    }

    @Override
    public Header header(String name) {
        for (Header header : this.origin) {
            if (name.equalsIgnoreCase(header.name())) {
                return header;
            }
        }

        throw new NoSuchElementException("Header with name : " + name + " not found");
    }

    @Override
    public boolean contains(String name) {
        try {
            this.header(name);
            return true;
        } catch (NoSuchElementException ignored) {
            return false;
        }
    }

    @Override
    public Iterator<Header> iterator() {
        return this.origin.iterator();
    }
}
