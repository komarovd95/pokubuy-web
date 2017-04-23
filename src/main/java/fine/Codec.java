package fine;

import java.util.Optional;

public interface Codec<E, D> {
    Optional<D> decode(E encoded);
    E encode(D decoded) throws EncodeException;
}
