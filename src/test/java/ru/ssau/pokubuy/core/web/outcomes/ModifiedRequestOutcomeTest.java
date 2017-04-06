package ru.ssau.pokubuy.core.web.outcomes;

import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;

public class ModifiedRequestOutcomeTest {
    @Test
    public void testApply() throws Exception {
        HttpServletRequest mockRq = Mockito.mock(HttpServletRequest.class);

        new ModifiedRequestOutcome(
            (request, response) -> assertEquals(mockRq, request),
            r -> mockRq
        );
    }
}