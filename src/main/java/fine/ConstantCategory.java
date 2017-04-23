package fine;

import java.io.Serializable;

public final class ConstantCategory implements Category, Serializable {
    private final long id;
    private final String name;
    private final transient Goods goods;

    public ConstantCategory(final long id, final String name, final Goods goods) {
        this.id = id;
        this.name = name;
        this.goods = goods;
    }

    @Override
    public long id() {
        return this.id;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public Goods goods() {
        return this.goods;
    }
}
