package last;

import java.io.IOException;
import java.util.function.Supplier;

public final class RdFork implements Render {
    private final Supplier<? extends Boolean> forkCondition;
    private final Render onTrue;
    private final Render onFalse;

    public RdFork(final Supplier<? extends Boolean> forkCondition,
                  final Render onTrue, final Render onFalse) {
        this.forkCondition = forkCondition;
        this.onTrue = onTrue;
        this.onFalse = onFalse;
    }

    @Override
    public void render(Engine engine) throws IOException {
        if (this.forkCondition.get()) {
            this.onTrue.render(engine);
        } else {
            this.onFalse.render(engine);
        }
    }
}
