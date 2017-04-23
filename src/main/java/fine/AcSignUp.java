package fine;

import ru.ssau.pokubuy.core.validation.ValidationException;

import java.io.IOException;
import java.util.Optional;

public final class AcSignUp implements Action {
    private final String forwardBack;
    private final transient Users users;
    private final transient Auth auth;

    public AcSignUp(final String forwardBack, final Users users, final Auth auth) {
        this.forwardBack = forwardBack;
        this.users = users;
        this.auth = auth;
    }

    @Override
    public Response act(final Request request) throws IOException {
        try {
            User requestUser = this.retrieveUserFromRequest(request);
            Optional<User> foundUser = this.users.user(requestUser.name());
            if (foundUser.isPresent()) {
                return new RsForward(
                    this.forwardBack,
                    new ErrorAttribute("Пользователь с таким именем уже существует")
                );
            } else {
                return this.auth.authenticate(
                        new RsRedirect.Query(request),
                        new PbUser(
                                this.users.add(
                                        requestUser.name(),
                                        requestUser.password(),
                                        Role.USER
                                )
                        ).toPrincipal()
                );
            }
        } catch (ValidationException | AuthException e) {
            return new RsForward(
                    this.forwardBack,
                    new ErrorAttribute(e)
            );
        }
    }

    private User retrieveUserFromRequest(final Request request) {
        return new EncryptedUser(
                new ValidUser(
                        new FormUser(request)
                )
        );
    }
}
