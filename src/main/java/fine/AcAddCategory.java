package fine;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Function;

public final class AcAddCategory implements Action {
    private final transient Categories categories;
    private final Function<Attribute, Action> onSuccess;
    private final Function<Attribute, Action> onError;

    public AcAddCategory(final Categories categories, final Function<Attribute, Action> onSuccess,
                         final Function<Attribute, Action> onError) {
        this.categories = categories;
        this.onSuccess = onSuccess;
        this.onError = onError;
    }

    @Override
    public Response act(final Request request) throws IOException {
        Optional<String> categoryName = new RqForm(request).value("category_name");
        if (categoryName.isPresent()) {
            try {
                Category added = this.categories.add(categoryName.get());
                return this.onSuccess.apply(new NamedAttribute("success", new ConstantCategory(
                        added.id(),
                        added.name(),
                        new ConstantGoods(added.goods())
                ))).act(request);
            } catch (IOException e) {
                return this.onError.apply(new ErrorAttribute("Категория с таким названием уже существует"))
                        .act(request);
            }
        }

        return new RsRedirect();
    }
}
