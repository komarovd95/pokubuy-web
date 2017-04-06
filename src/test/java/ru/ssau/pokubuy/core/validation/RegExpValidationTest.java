package ru.ssau.pokubuy.core.validation;

import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class RegExpValidationTest {
    @Test
    public void testValid() throws Exception {
        Validation<String> regExpValidation = new RegExpValidation(Pattern.compile("^[a-z]+$"));

        assertEquals("abc", regExpValidation.validate("abc"));
    }

    @Test(expected = ValidationException.class)
    public void testNotValid() throws Exception {
        Validation<String> regExpValidation = new RegExpValidation(Pattern.compile("^[a-z]+$"));

        regExpValidation.validate("ABC");
    }

    @Test(expected = ValidationException.class)
    public void testNotValidNull() throws Exception {
        Validation<String> regExpValidation = new RegExpValidation(Pattern.compile("^[a-z]+$"));

        regExpValidation.validate(null);
    }
}