package fine;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class AcLogged extends AcWrap {
    private final String loggerName;

    public AcLogged(final Action origin) {
        this(origin, AcLogged.class.getName());
    }

    public AcLogged(final Action origin, final String loggerName) {
        super(origin);

        this.loggerName = loggerName;
    }

    @Override
    public Response act(final Request request) throws IOException {
        Response response = super.act(request);

        Logger.getLogger(this.loggerName).log(Level.INFO,
                String.format("Requested URL: %s [%s] Response status [%s]",
                        request.url(), request.method(), response.status())
        );

        return response;
    }
}
