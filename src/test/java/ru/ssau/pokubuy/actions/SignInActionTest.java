package ru.ssau.pokubuy.actions;

import org.junit.Test;

import static org.junit.Assert.*;

public class SignInActionTest {
    @Test
    public void testPath() throws Exception {
        SignInAction signInAction = new SignInAction();

        assertEquals("/signin", signInAction.path());
    }

    @Test
    public void testPage() throws Exception {
        SignInAction signInAction = new SignInAction();

        assertEquals("/WEB-INF/signin.jsp", signInAction.page());
    }

}