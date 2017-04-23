package fine;

import java.io.IOException;
import java.util.Optional;

public interface Categories extends Iterable<Category> {
    Category add(String name) throws IOException;
    Optional<Category> category(long id);
    void remove(long categoryId) throws IOException;
}
