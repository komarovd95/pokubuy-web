package fine;

import ru.ssau.pokubuy.core.validation.ValidationException;

import java.util.Arrays;
import java.util.Optional;

public final class PbUser extends UserWrap {
    public PbUser(final User origin) {
        super(origin);
    }

    public Principal toPrincipal() {
        return new Principal() {
            @Override
            public String identity() {
                try {
                    return new Codec().encode(
                        new String[] {
                            PbUser.this.name(),
                            PbUser.this.password()
                        }
                     );
                } catch (ValidationException | EncodeException e) {
                    return Principal.ANONYMOUS;
                }
            }

            @Override
            public boolean hasRole(Role role) {
                return PbUser.this.role().equals(role);
            }
        };
    }

    public static final class Codec implements fine.Codec<String, String[]> {
        @Override
        public Optional<String[]> decode(final String encoded) {
            String[] split = encoded.split(":", 2);
            if (split.length == 2) {
                return Optional.of(split);
            } else {
                return Optional.empty();
            }
        }

        @Override
        public String encode(final String[] decoded) throws EncodeException {
            if (decoded.length == 2) {
                return decoded[0] + ":" + decoded[1];
            } else {
                throw new EncodeException("Cannot encode: " + Arrays.toString(decoded));
            }
        }
    }
}
