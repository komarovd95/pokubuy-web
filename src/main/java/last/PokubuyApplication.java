package last;

import javax.servlet.annotation.WebServlet;
import java.time.LocalDateTime;

//@WebServlet("/")
public final class PokubuyApplication extends Application {
    @Override
    protected Front front() {
        LocalDateTime buildTime = LocalDateTime.now();

        return new FtServlet(
            new EgServlet.Factory(),
            new RqServlet.Factory(),
            new EtRoute(
                new MtExact("/"),
                new RdJsp("/WEB-INF/index.jsp")
            ),
            new EtFork(
                new MtExact("/signin"),
                new RdJsp("/WEB-INF/signin.jsp"),
                new RdJsp("/WEB-INF/index.jsp")
            ),
            new EtRoute(
                new MtGet(
                    new MtOr(
                        new MtStart("/css/"),
                        new MtStart("/js/")
                    )
                ),
                new RdAsset()
            ),
            new EtRoute(
                (method, url) -> true,
                new RdBody(
                    new RsWithStatus(
                        new RsEmpty(), 404
                    )
                )
            )
        );
    }
}
