package fine;

import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

public final class ConstantGoods implements Goods {
    private final transient Goods origin;

    public ConstantGoods(final Goods origin) {
        this.origin = origin;
    }

    @Override
    public Iterator<Good> iterator() {
        Iterator<Good> originIterator = this.origin.iterator();
        return new Iterator<Good>() {
            @Override
            public boolean hasNext() {
                return originIterator.hasNext();
            }

            @Override
            public Good next() {
                return ConstantGoods.this.toConstant(originIterator.next());
            }
        };
    }

    @Override
    public Good add(final String title, final String description) throws IOException {
        throw new UnsupportedOperationException("Cannot modify constant object");
    }

    @Override
    public Optional<Good> good(final long id) throws IOException {
        return this.origin.good(id).map(this::toConstant);
    }

    @Override
    public void remove(final long id) throws IOException {
        throw new UnsupportedOperationException("Cannot modify constant object");
    }

    @Override
    public void update(final long id, final String title, final String description,
                       final long categoryId) throws IOException {
        throw new UnsupportedOperationException("Cannot modify constant object");
    }

    private Good toConstant(final Good good) {
        Category category = good.category();

        return new ConstantGood(
                good.id(),
                good.title(),
                good.description(),
                new ConstantCategory(
                        category.id(),
                        category.name(),
                        new ConstantGoods(
                                category.goods()
                        )
                )
        );
    }
}
