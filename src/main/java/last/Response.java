package last;

public interface Response {
    int status();
    Iterable<Header> headers();
    CharSequence body();
}
