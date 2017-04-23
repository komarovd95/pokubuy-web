package fine;

import java.io.IOException;

public final class AcSignIn implements Action {
    private final transient Auth auth;
    private final String forwardBack;

    public AcSignIn(final Auth auth, final String forwardBack) {
        this.auth = auth;
        this.forwardBack = forwardBack;
    }

    @Override
    public Response act(final Request request) throws IOException {
        try {
            return this.auth.authenticate(
                new RsRedirect.Query(request),
                new PbUser(
                    this.retrieveUserFromRequest(request)
                ).toPrincipal()
            );
        } catch (AuthException e) {
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
