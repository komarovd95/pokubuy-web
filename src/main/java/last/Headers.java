package last;

public interface Headers extends Iterable<Header> {
    Header header(String name);
    boolean contains(String name);
}
