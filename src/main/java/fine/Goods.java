package fine;

import java.io.IOException;
import java.util.Optional;

public interface Goods extends Iterable<Good> {
    Good add(String title, String description) throws IOException;
    Optional<Good> good(long id) throws IOException;
    void remove(long id) throws IOException;
    void update(long id, String title, String description, long categoryId) throws IOException;
}
