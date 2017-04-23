package fine;

import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

public final class ConstantCategories implements Categories {
    private final transient Categories origin;

    public ConstantCategories(final Categories origin) {
        this.origin = origin;
    }

    @Override
    public Category add(final String name) throws IOException {
        throw new UnsupportedOperationException("Cannot modify constant object");
    }

    @Override
    public Optional<Category> category(long id) {
        return this.origin.category(id).map(this::toConstant);
    }

    @Override
    public void remove(long categoryId) throws IOException {
        throw new UnsupportedOperationException("Cannot modify constant object");
    }

    @Override
    public Iterator<Category> iterator() {
        Iterator<Category> originIterator = this.origin.iterator();
        return new Iterator<Category>() {
            @Override
            public boolean hasNext() {
                return originIterator.hasNext();
            }

            @Override
            public Category next() {
                return ConstantCategories.this.toConstant(originIterator.next());
            }
        };
    }

    private Category toConstant(final Category c) {
        return new ConstantCategory(
                c.id(),
                c.name(),
                new ConstantGoods(c.goods())
        );
    }
}
