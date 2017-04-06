package ru.ssau.pokubuy.core.web.responses;

import org.junit.Test;
import org.mockito.Mockito;
import ru.ssau.pokubuy.core.web.Request;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

public class JspPageTest {
    private static final String URI = "/example";

//    private static final class FakeRequest implements Request {
//        @Override
//        public HttpServletRequest asHttpServletRequest() {
//            HttpServletRequest rqMock = Mockito.mock(HttpServletRequest.class);
//            RequestDispatcher rqDispatcherMock = Mockito.mock(RequestDispatcher.class);
//
//            Mockito.when(rqMock.getRequestDispatcher(URI)).thenReturn(rqDispatcherMock);
//
//            return rqMock;
//        }
//    }
//
//    @Test
//    public void testRender() throws Exception {
//        FakeRequest fakeRequest = new FakeRequest();
//        JspPage jspPage = new JspPage(URI, attributes, fakeRequest);
//
//        jspPage.render(Mockito.any());
//
//        Mockito.verify(fakeRequest.asHttpServletRequest());
//    }
}