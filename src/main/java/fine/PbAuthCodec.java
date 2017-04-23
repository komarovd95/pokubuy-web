package fine;

import java.util.Base64;
import java.util.Optional;

public final class PbAuthCodec implements Codec<String, Principal> {
    private final transient Users pbUsers;

    public PbAuthCodec(final Users pbUsers) {
        this.pbUsers = pbUsers;
    }

    @Override
    public Optional<Principal> decode(final String encoded) {
        return new PbUser.Codec().decode(new String(Base64.getDecoder().decode(encoded.getBytes())))
                .filter(nameAndPass -> nameAndPass.length == 2)
                .flatMap(nameAndPass -> this.pbUsers.user(nameAndPass[0], nameAndPass[1]))
                .map(PbUser::new)
                .map(PbUser::toPrincipal);
    }

    @Override
    public String encode(final Principal decoded) throws EncodeException {
        Optional<User> foundUser = new PbUser.Codec().decode(decoded.identity())
                .filter(nameAndPass -> nameAndPass.length == 2)
                .flatMap(nameAndPass -> this.pbUsers.user(nameAndPass[0], nameAndPass[1]));
        if (foundUser.isPresent()) {
            return Base64.getEncoder().encodeToString(decoded.identity().getBytes());
        } else {
            throw new EncodeException("Неверная пара имя/пароль");
        }
    }
}
