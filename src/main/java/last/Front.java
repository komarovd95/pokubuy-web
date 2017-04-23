package last;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Front {
    void act(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
