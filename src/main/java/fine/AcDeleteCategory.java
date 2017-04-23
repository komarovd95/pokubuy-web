package fine;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class AcDeleteCategory implements Action {
    private final transient Categories categories;

    public AcDeleteCategory(final Categories categories) {
        this.categories = categories;
    }

    @Override
    public Response act(Request request) throws IOException {
        Matcher matcher = Pattern.compile("^/categories/([0-9]+)/delete").matcher(request.url());
        if (matcher.find()) {
            long goodId = Long.parseLong(matcher.group(1));

            this.categories.remove(goodId);

            return new RsRedirect();
        }

        return new RsNotFound();
    }
}
