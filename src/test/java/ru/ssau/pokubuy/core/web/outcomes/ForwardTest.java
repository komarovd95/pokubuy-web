package ru.ssau.pokubuy.core.web.outcomes;

import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForwardTest {
    @Test
    public void testApply() throws Exception {
        RequestDispatcher requestDispatcher = Mockito.mock(RequestDispatcher.class);
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        Forward forward = new Forward(requestDispatcher);

        forward.apply(request, response);

        Mockito.verify(requestDispatcher, Mockito.only()).forward(request, response);
    }
}