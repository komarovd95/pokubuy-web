package ru.ssau.pokubuy.domain.users;

import org.junit.Test;
import ru.ssau.pokubuy.core.validation.ValidationException;

import static org.junit.Assert.*;

public class ValidUserTest {
    @Test
    public void testName() throws Exception {
        User fakeUser = new FakeUser("user");

        User validUser = new ValidUser(fakeUser);

        assertEquals(fakeUser.name(), validUser.name());
    }

    @Test
    public void testPassword() throws Exception {
        User fakeUser = new FakeUser("pass");

        User validUser = new ValidUser(fakeUser);

        assertEquals(fakeUser.password(), validUser.password());
    }

    @Test(expected = ValidationException.class)
    public void testNameNull() throws Exception {
        User validUser = new ValidUser(
            new FakeUser(null)
        );

        validUser.name();
    }

    @Test(expected = ValidationException.class)
    public void testNameLessThenFourSymbols() throws Exception {
        User validUser = new ValidUser(
            new FakeUser(null)
        );

        validUser.name();
    }

    @Test(expected = ValidationException.class)
    public void testNameMoreThenTwentySymbols() throws Exception {
        User validUser = new ValidUser(
            new FakeUser("this string is more then 20 symbols")
        );

        validUser.name();
    }

    @Test(expected = ValidationException.class)
    public void testNameDisallowedSymbols() throws Exception {
        User validUser = new ValidUser(
            new FakeUser("$$$$")
        );

        validUser.name();
    }

    @Test(expected = ValidationException.class)
    public void testPassNull() throws Exception {
        User validUser = new ValidUser(
            new FakeUser(null)
        );

        validUser.password();
    }

    @Test(expected = ValidationException.class)
    public void testPassLessThenFourSymbols() throws Exception {
        User validUser = new ValidUser(
            new FakeUser(null)
        );

        validUser.password();
    }

    @Test(expected = ValidationException.class)
    public void testPassMoreThenTwentySymbols() throws Exception {
        User validUser = new ValidUser(
            new FakeUser("this string is more then 20 symbols")
        );

        validUser.password();
    }

    @Test(expected = ValidationException.class)
    public void testPassDisallowedSymbols() throws Exception {
        User validUser = new ValidUser(
            new FakeUser("$$$$")
        );

        validUser.password();
    }
}