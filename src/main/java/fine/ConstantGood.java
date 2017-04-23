package fine;

import java.io.Serializable;

public final class ConstantGood implements Good, Serializable {
    private final long id;
    private final String title;
    private final String description;
    private final Category category;

    public ConstantGood(final long id, final String title, final String description,
                        final Category category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
    }

    @Override
    public long id() {
        return this.id;
    }

    @Override
    public String title() {
        return this.title;
    }

    @Override
    public String description() {
        return this.description;
    }

    @Override
    public Category category() {
        return this.category;
    }
}
