package fine;

import java.io.IOException;
import java.util.Optional;

public interface Users extends Iterable<User> {
    Optional<User> user(String name, String password);
    Optional<User> user(String name);
    User add(String name, String password, Role role) throws IOException;
}
