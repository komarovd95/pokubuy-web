package fine;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;

public final class RsHttpServlet extends RsWrap {
    public RsHttpServlet(final Response origin) {
        super(origin);
    }

    public void out(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        response.setStatus(this.status());

        for (Header header : this.headers()) {
            response.setHeader(header.name(), header.value());
        }

        if (!this.tryForward(request, response) && !this.tryPipeAsset(request, response)) {
            this.writeBody(request, response);
        }
    }

    private boolean tryForward(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        RsForward.Smart forward = new RsForward.Smart(this.origin);
        Optional<String> forwardTo = forward.forwardTo();
        if (forwardTo.isPresent()) {
            for (Attribute attribute : forward.attributes()) {
                request.setAttribute(attribute.name(), attribute.value());
            }

            request.getRequestDispatcher(forwardTo.get()).forward(request, response);

            return true;
        }

        return false;
    }

    private boolean tryPipeAsset(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        RsAsset.Smart asset = new RsAsset.Smart(this.origin);

        Optional<String> hasAsset = asset.hasAsset();
        if (hasAsset.isPresent()) {
            request.getServletContext().getNamedDispatcher(hasAsset.get())
                    .forward(request, response);

            return true;
        }

        return false;
    }

    private void writeBody(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        try (final InputStream body = this.origin.body();
             final OutputStream resp = response.getOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = body.read(buffer)) != -1) {
                resp.write(buffer, 0, bytesRead);
            }

            resp.flush();
        }
    }
}
