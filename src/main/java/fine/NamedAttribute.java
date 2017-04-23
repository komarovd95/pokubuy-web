package fine;

public final class NamedAttribute implements Attribute {
    private final String name;
    private final Object value;

    public NamedAttribute(final String name, final Object value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public Object value() {
        return this.value;
    }
}
