package fine;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class AcDeleteGood implements Action {
    private final transient Goods goods;

    public AcDeleteGood(final Goods goods) {
        this.goods = goods;
    }

    @Override
    public Response act(final Request request) throws IOException {
        Matcher matcher = Pattern.compile("^/goods/([0-9]+)/delete").matcher(request.url());
        if (matcher.find()) {
            long goodId = Long.parseLong(matcher.group(1));

            this.goods.remove(goodId);

            return new RsRedirect.Query(request);
        }

        return new RsNotFound();
    }
}
