package fine;

import java.util.function.Supplier;

public final class HdLazy implements Header {
    private final String name;
    private final Supplier<? extends String> valueFactory;

    public HdLazy(final String name, final Supplier<? extends String> valueFactory) {
        this.name = name;
        this.valueFactory = valueFactory;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public String value() {
        return this.valueFactory.get();
    }
}
