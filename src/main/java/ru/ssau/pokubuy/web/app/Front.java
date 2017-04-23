package ru.ssau.pokubuy.web.app;

import ru.ssau.pokubuy.web.Action;
import ru.ssau.pokubuy.web.Request;
import ru.ssau.pokubuy.web.requests.RqHttp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Front extends HttpServlet {
    protected abstract Action app();

    protected Request constructRequest(HttpServletRequest request) {
        return new RqHttp(request);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);

        this.app().act(this.constructRequest(request)).send(request, response);

//        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);

//        String method = request.getMethod();
//
//        if (method.equals("GET") && !this.isModified(request, response)) {
//            return;
//        }
//
//        this.app().act(this.constructRequest(request));
    }

    private boolean isModified(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean modified;

        long lastModified = getLastModified(request);

        if (lastModified >= 0) {
            long ifModifiedSince = request.getDateHeader("If-Modified-Since");

            modified = ifModifiedSince < lastModified;

            if (modified) {
                setLastModifiedIfAbsent(response, lastModified);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
            }
        } else {
            modified = true;
        }

        return modified;
    }

    private void setLastModifiedIfAbsent(HttpServletResponse resp, long lastModified) {
        if (!resp.containsHeader("Last-Modified")) {
            resp.setDateHeader("Last-Modified", lastModified);
        }
    }
}
