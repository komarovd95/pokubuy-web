package fine;

public final class ErrorAttribute implements Attribute {
    private final String errorMessage;

    public ErrorAttribute(final Throwable throwable) {
        this(throwable.getLocalizedMessage());
    }

    public ErrorAttribute(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String name() {
        return "error";
    }

    @Override
    public Object value() {
        return this.errorMessage;
    }
}
