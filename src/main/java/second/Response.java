package second;

public interface Response<B> {
    Iterable<String> headers();
    B body();
}
