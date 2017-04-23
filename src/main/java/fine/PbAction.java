package fine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class PbAction implements Action {
    private final transient Auth auth;
    private final transient Users users;
    private final transient Categories categories;
    private final String path;
    private final Iterable<Attribute> attributes;

    public PbAction(final Auth auth, final Users users, final Categories categories,
                    final String path, final Attribute... attributes) {
        this(auth, users, categories, path, Arrays.asList(attributes));
    }

    public PbAction(final Auth auth, final Users users, final Categories categories,
                    final String path, final Iterable<Attribute> attributes) {
        this.auth = auth;
        this.users = users;
        this.categories = categories;
        this.path = path;
        this.attributes = attributes;
    }

    @Override
    public Response act(final Request request) throws IOException {
        List<Attribute> attributes = new ArrayList<>();

        this.auth.authenticated(request).map(Principal::identity)
                .flatMap(identity -> new PbUser.Codec().decode(identity))
                .flatMap(nameAndPass -> this.users.user(nameAndPass[0], nameAndPass[1]))
                .map(UserAttribute::new)
                .ifPresent(attributes::add);

        List<Category> categories = new ArrayList<>();
        this.categories.forEach(categories::add);

        attributes.add(new NamedAttribute("categories", categories));

        this.attributes.forEach(attributes::add);

        return new RsForward(this.path, attributes);
    }
}
