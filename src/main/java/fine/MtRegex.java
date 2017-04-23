package fine;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Pattern;

public final class MtRegex implements Matching {
    private final Pattern pattern;

    public MtRegex(final String pattern) {
        this(Pattern.compile(pattern));
    }

    public MtRegex(final Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public boolean matches(final Request request) throws IOException {
        try {
            return this.pattern.matcher(new URI(request.url()).getPath()).matches();
        } catch (URISyntaxException e) {
            return false;
        }
    }
}
