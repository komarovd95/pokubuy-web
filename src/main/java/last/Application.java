package last;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Application extends HttpServlet {
    private Front front;

    protected abstract Front front();

    @Override
    public void init(final ServletConfig config) {
        this.front = this.front();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.front.act(request, response);
    }
}
