package fine;

import java.io.IOException;

@FunctionalInterface
public interface Matching {
    boolean matches(Request request) throws IOException;

    final class True implements Matching {
        @Override
        public boolean matches(final Request request) throws IOException {
            return true;
        }
    }

    final class False implements Matching {
        @Override
        public boolean matches(final Request request) throws IOException {
            return false;
        }
    }
}
