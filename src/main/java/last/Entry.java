package last;

public interface Entry {
    boolean matches(Request request);
    Render render(Request request);
}
