package fine;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public final class AcAddGood implements Action {
    private final transient Categories categories;
    private final Function<Attribute, Action> onSuccess;
    private final Function<Attribute, Action> onError;

    public AcAddGood(final Categories categories, final Function<Attribute, Action> onSuccess,
                     final Function<Attribute, Action> onError) {
        this.categories = categories;
        this.onSuccess = onSuccess;
        this.onError = onError;
    }

    @Override
    public Response act(final Request request) throws IOException {
        Map<String, String> values = new RqForm(request).values();

        if (values.containsKey("title") && values.containsKey("description")
                && values.containsKey("category")) {
            try {
                long categoryId = Long.parseLong(values.get("category"));

                Optional<Category> found = this.categories.category(categoryId);
                if (found.isPresent()) {
                    Good added = found.get().goods().add(
                            values.get("title"), values.get("description")
                    );
                    return this.onSuccess.apply(new NamedAttribute("success", added.title()))
                            .act(request);
                } else {
                    return this.onError.apply(new ErrorAttribute("Укажите категорию"))
                            .act(request);
                }
            } catch (NumberFormatException e) {
                return this.onError.apply(new ErrorAttribute("Укажите категорию"))
                        .act(request);
            } catch (IOException e) {
                return this.onError.apply(
                        new ErrorAttribute("Товар с таким именем уже существует")
                ).act(request);
            }
        } else {
            return this.onError.apply(new ErrorAttribute("Заполните форму"))
                    .act(request);
        }
    }
}
