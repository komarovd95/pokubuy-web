package ru.ssau.pokubuy.domain.users;

import ru.ssau.pokubuy.core.validation.ValidationException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class EcUser implements User {
    private final User origin;
    private final String algorithm;

    public EcUser(User origin) {
        this(origin, "SHA-512");
    }

    public EcUser(User origin, String algorithm) {
        this.origin = origin;
        this.algorithm = algorithm;
    }

    @Override
    public String name() throws ValidationException {
        return this.origin.name();
    }

    @Override
    public String password() throws ValidationException {
        try {
            MessageDigest instance = MessageDigest.getInstance(this.algorithm);
            instance.update(this.origin.password().getBytes());

            return new String(instance.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
