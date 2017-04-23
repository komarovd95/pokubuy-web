package last;

public interface Request {
    String method();
    String url();
    Headers headers();
    CharSequence body();

    class Smart {
        private final transient Request request;

        public Smart(final Request request) {
            this.request = request;
        }

        public boolean matches(final Matching matching) {
            return matching.matches(this.request.method(), this.request.url());
        }
    }
}
