package common;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class ApplicationController extends HttpServlet {
    private Front<? super HttpServletRequest, ? extends Response> front;

    protected abstract Front<? super HttpServletRequest, ? extends Response> front();

    @Override
    public void init(ServletConfig config) {
        this.front = this.front();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Request req = this.front.request(request);
        Response res = this.front.response(req);


        res.send(request, response);
    }
}
