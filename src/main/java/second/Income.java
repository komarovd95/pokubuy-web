package second;

public interface Income {
    Request request();
    Outcome asset(Response<?> response);
    Outcome render(Response<? extends View> response);
    Outcome out(Response<?> response);
}
