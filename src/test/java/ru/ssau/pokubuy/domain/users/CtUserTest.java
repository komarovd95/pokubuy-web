package ru.ssau.pokubuy.domain.users;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CtUserTest {
    private User ctUser;

    @Before
    public void setUp() throws Exception {
        this.ctUser = new CtUser("user", "pass");
    }

    @Test
    public void testName() throws Exception {
        assertEquals("user", this.ctUser.name());
    }

    @Test
    public void testPassword() throws Exception {
        assertEquals("pass", this.ctUser.password());
    }
}