package fine;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Application extends HttpServlet {
    public final static String ROOT = "/";

    private Action frontAction;

    protected abstract Action frontAction();

    @Override
    public void init(final ServletConfig config) {
        this.frontAction = this.frontAction();
    }

    @Override
    protected void service(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        new RsHttpServlet(this.frontAction.act(new RqHttpServlet(request)))
                .out(request, response);
    }
}
