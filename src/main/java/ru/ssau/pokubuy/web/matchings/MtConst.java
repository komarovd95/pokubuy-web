package ru.ssau.pokubuy.web.matchings;

import ru.ssau.pokubuy.web.Matching;

public abstract class MtConst implements Matching {
    private final boolean constant;

    private MtConst(boolean constant) {
        this.constant = constant;
    }

    @Override
    public boolean matches(String method, String path) {
        return this.constant;
    }

    public static final class True extends MtConst {
        public True() {
            super(true);
        }
    }

    public static final class False extends MtConst {
        public False() {
            super(false);
        }
    }
}
