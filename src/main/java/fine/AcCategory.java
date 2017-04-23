package fine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class AcCategory implements Action {
    private final transient Categories categories;
    private final Function<Iterable<Attribute>, Action> onFound;

    public AcCategory(final Categories categories,
                      final Function<Iterable<Attribute>, Action> onFound) {
        this.categories = categories;
        this.onFound = onFound;
    }

    @Override
    public Response act(final Request request) throws IOException {
        Matcher matcher = Pattern.compile("^/categories/([0-9]+)$").matcher(request.url());
        if (matcher.find()) {
            long categoryId = Long.parseLong(matcher.group(1));

            Optional<Category> category = this.categories.category(categoryId);
            if (category.isPresent()) {
                Category got = category.get();
                List<Good> goods = new ArrayList<>();
                got.goods().forEach(goods::add);

                return this.onFound.apply(Arrays.asList(
                            new NamedAttribute("category", got),
                            new NamedAttribute("goods", goods)
                        )
                ).act(request);
            }

        }

        return new RsNotFound();
    }
}
