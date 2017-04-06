package ru.ssau.pokubuy.core.web.requests;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import ru.ssau.pokubuy.core.web.Request;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;

public class HttpRequestTest {
    @Test
    public void testAsHttpServletRequest() throws Exception {
        HttpServletRequest mock = Mockito.mock(HttpServletRequest.class);

        Request request = new HttpRequest(mock);

//        assertEquals(mock, request.asHttpServletRequest());
    }

}