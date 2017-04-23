package fine;

public final class HdSimple implements Header {
    private final String name;
    private final String value;

    public HdSimple(final String name, final String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public String value() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.name + ": " + this.value;
    }
}
