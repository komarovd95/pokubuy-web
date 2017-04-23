package fine;

import java.io.Serializable;

public interface Attribute extends Serializable {
    String name();
    Object value();
}
