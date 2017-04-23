package common;

public interface Endpoint<R> {
    boolean matches(Request request);
    R act(Request request);
}
