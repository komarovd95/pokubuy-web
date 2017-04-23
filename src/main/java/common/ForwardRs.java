package common;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class ForwardRs implements Response {
    private final String page;

    public ForwardRs(String page) {
        this.page = page;
    }

    @Override
    public void send(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(this.page);
        requestDispatcher.forward(request, response);
    }
}
