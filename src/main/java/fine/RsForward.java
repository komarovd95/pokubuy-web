package fine;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

public final class RsForward implements Response {
    private final static String FORWARD_HEADER = "X-Forwarded-To";

    private final String path;
    private final Iterable<Attribute> attributes;

    public RsForward(final String path, final Attribute... attributes) {
        this(path, Arrays.asList(attributes));
    }

    public RsForward(final String path, final Iterable<Attribute> attributes) {
        this.path = path;
        this.attributes = attributes;
    }

    @Override
    public int status() {
        return 200;
    }

    @Override
    public Iterable<Header> headers() {
        return Collections.singleton(
            new HdSimple(RsForward.FORWARD_HEADER, this.path)
        );
    }

    @Override
    public InputStream body() throws IOException {
        try (final ByteArrayOutputStream bytes = new ByteArrayOutputStream();
             final ObjectOutputStream object = new ObjectOutputStream(bytes)) {
            object.writeObject(this.attributes);
            object.flush();

            return new ByteArrayInputStream(bytes.toByteArray());
        }
    }

    public static final class Smart {
        private final transient Response origin;

        public Smart(final Response origin) {
            this.origin = origin;
        }

        public Optional<String> forwardTo() {
            for (Header header : this.origin.headers()) {
                if (RsForward.FORWARD_HEADER.equalsIgnoreCase(header.name())) {
                    return Optional.of(header.value());
                }
            }

            return Optional.empty();
        }

        @SuppressWarnings("unchecked")
        public Iterable<Attribute> attributes() {
            try (final InputStream body = this.origin.body();
                 final ObjectInputStream object = new ObjectInputStream(body)) {
                return (Iterable<Attribute>) object.readObject();
            } catch (IOException | ClassNotFoundException e) {
                return Collections.emptyList();
            }
        }
    }
}
