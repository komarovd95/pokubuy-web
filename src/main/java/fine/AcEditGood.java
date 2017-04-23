package fine;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class AcEditGood implements Action {
    private final transient Goods goods;
    private final Function<Iterable<Attribute>, Action> onError;

    public AcEditGood(final Goods goods, final Function<Iterable<Attribute>, Action> onError) {
        this.goods = goods;
        this.onError = onError;
    }

    @Override
    public Response act(final Request request) throws IOException {
        Matcher matcher = Pattern.compile("^/goods/([0-9]+)/edit").matcher(request.url());
        if (matcher.find()) {
            long goodId = Long.parseLong(matcher.group(1));

            Map<String, String> values = new RqForm(request).values();
            if (values.containsKey("title") && values.containsKey("description")
                    && values.containsKey("category")) {
                try {
                    long categoryId = Long.parseLong(values.get("category"));

                    this.goods.update(
                            goodId,
                            values.get("title"),
                            values.get("description"),
                            categoryId
                    );

                    return new RsRedirect("/categories/" + categoryId);
                } catch (NumberFormatException e) {
                    return this.onError.apply(
                            Arrays.asList(
                                    new ErrorAttribute("Укажите категорию"),
                                    new NamedAttribute(
                                            "good",
                                            new ConstantGoods(this.goods).good(goodId).get()
                                    )
                            )
                    ).act(request);
                } catch (IOException e) {
                    return this.onError.apply(
                            Arrays.asList(
                                new ErrorAttribute("Товар с таким именем уже существует"),
                                new NamedAttribute(
                                    "good",
                                    new ConstantGoods(this.goods).good(goodId).get()
                                )
                            )
                    ).act(request);
                }
            } else {
                return this.onError.apply(
                        Collections.singleton(
                                new ErrorAttribute("Заполните форму")
                        )
                ).act(request);
            }
        }

        return new RsNotFound();
    }
}
