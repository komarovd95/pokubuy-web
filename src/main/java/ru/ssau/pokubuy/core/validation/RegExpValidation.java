package ru.ssau.pokubuy.core.validation;

import java.util.regex.Pattern;

public final class RegExpValidation implements Validation<String> {
    private final Pattern pattern;

    public RegExpValidation(String pattern) {
        this(Pattern.compile(pattern));
    }

    public RegExpValidation(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public String validate(String s) throws ValidationException {
        if (s != null && this.pattern.matcher(s).matches()) {
            return s;
        } else {
            throw new ValidationException(
                String.format(
                    "Given value does not match pattern (Value: \"%s\" Pattern: \"%s\"",
                    s,
                    this.pattern
                )
            );
        }
    }
}
