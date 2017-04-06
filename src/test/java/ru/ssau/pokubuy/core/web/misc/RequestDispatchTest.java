package ru.ssau.pokubuy.core.web.misc;

import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;

public class RequestDispatchTest {
    @Test
    public void testMap() throws Exception {
        String uri = "/example";

        RequestDispatch requestDispatch = new RequestDispatch(uri);

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);

        requestDispatch.map(request);

        Mockito.verify(request, Mockito.only()).getRequestDispatcher(uri);
    }
}