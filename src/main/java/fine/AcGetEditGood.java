package fine;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class AcGetEditGood implements Action {
    private final transient Goods goods;
    private final Function<Attribute, Action> onFound;

    public AcGetEditGood(final Goods goods, final Function<Attribute, Action> onFound) {
        this.goods = goods;
        this.onFound = onFound;
    }

    @Override
    public Response act(final Request request) throws IOException {
        Matcher matcher = Pattern.compile("^/goods/([0-9]+)/edit$").matcher(request.url());
        if (matcher.find()) {
            long goodId = Long.parseLong(matcher.group(1));

            Optional<Good> good = this.goods.good(goodId);
            if (good.isPresent()) {
                return this.onFound.apply(new NamedAttribute("good", good.get()))
                        .act(request);
            }
        }

        return new RsNotFound();
    }
}
