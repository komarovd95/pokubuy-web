package fine;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Function;

public final class AcGetAddGood implements Action {
    private final Function<Iterable<Attribute>, Action> onSuccess;

    public AcGetAddGood(final Function<Iterable<Attribute>, Action> onSuccess) {
        this.onSuccess = onSuccess;
    }

    @Override
    public Response act(final Request request) throws IOException {
        try {
            Optional<String> categoryId = new QueryString(new URI(request.url()))
                    .single("categoryId");
            if (categoryId.isPresent()) {
                return this.onSuccess.apply(
                        Collections.singleton(
                                new NamedAttribute(
                                        "categoryId",
                                        categoryId.get()
                                )
                        )
                ).act(request);
            }
        } catch (URISyntaxException ignored) {}

        return this.onSuccess.apply(Collections.emptyList()).act(request);
    }
}
