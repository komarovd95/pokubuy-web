package fine;

import ru.ssau.pokubuy.core.validation.ValidationException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class EncryptedUser extends UserWrap {
    private final String algorithm;

    public EncryptedUser(final User origin) {
        this(origin, "SHA-512");
    }

    public EncryptedUser(final User origin, final String algorithm) {
        super(origin);

        this.algorithm = algorithm;
    }

    @Override
    public String password() throws ValidationException {
        try {
            MessageDigest instance = MessageDigest.getInstance(this.algorithm);
            instance.update(super.password().getBytes());

            byte[] bytes = instance.digest();

            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new ValidationException(e.getLocalizedMessage());
        }
    }
}
