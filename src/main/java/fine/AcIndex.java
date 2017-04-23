package fine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class AcIndex implements Action {
    private final transient Auth auth;
    private final transient Users users;

    public AcIndex(final Auth auth, final Users users) {
        this.auth = auth;
        this.users = users;
    }

    @Override
    public Response act(final Request request) throws IOException {
        List<Attribute> attributes = new ArrayList<>();

        this.auth.authenticated(request).map(Principal::identity)
                .flatMap(identity -> new PbUser.Codec().decode(identity))
                .flatMap(nameAndPass -> this.users.user(nameAndPass[0], nameAndPass[1]))
                .map(UserAttribute::new)
                .ifPresent(attributes::add);

        return new RsForward("/WEB-INF/index.jsp", attributes);
    }
}
