package ru.ssau.pokubuy.core.validation;

import org.junit.Test;

import static org.junit.Assert.*;

public class VerboseValidationTest {
    private static class FakeValidation<T> implements Validation<T> {
        @Override
        public T validate(T t) throws ValidationException {
            return t;
        }
    }

    private static class FailValidation<T> implements Validation<T> {
        @Override
        public T validate(T t) throws ValidationException {
            throw new ValidationException();
        }
    }

    @Test
    public void testValid() throws Exception {
        Validation<String> verboseValidation = new VerboseValidation<>(
            new FakeValidation<>(),
            "This is error message"
        );

        assertEquals("abc", verboseValidation.validate("abc"));
    }

    @Test
    public void testNotValid() throws Exception {
        String errorMessage = "This is error message";

        try {
            Validation<String> verboseValidation = new VerboseValidation<>(
                new FailValidation<>(),
                errorMessage
            );

            verboseValidation.validate("abc");

            fail("Should have thrown ValidationException but did not!");
        } catch(ValidationException e ) {
            assertEquals(errorMessage, e.getMessage());
        } catch (Exception e) {
            fail("Should have thrown ValidationException but did not!");
        }
    }
}