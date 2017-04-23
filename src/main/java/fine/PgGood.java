package fine;

import javax.sql.DataSource;

public final class PgGood implements Good {
    private final transient DataSource dataSource;
    private final long id;
    private final String title;
    private final String description;
    private final long categoryId;

    public PgGood(final DataSource dataSource, final long id, final String title,
                  final String description, final long categoryId) {
        this.dataSource = dataSource;
        this.id = id;
        this.title = title;
        this.description = description;
        this.categoryId = categoryId;
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
        return new PgCategory(this.dataSource, this.categoryId);
    }
}
