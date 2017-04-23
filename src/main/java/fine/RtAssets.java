package fine;

import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Pattern;

public final class RtAssets extends RtMatched {
    public RtAssets(final String... extensions) {
        this(Arrays.asList(extensions));
    }

    public RtAssets(final Iterable<String> extensions) {
        super(new RtGet((req) -> Optional.of(new RsAsset())), (req) -> {
            Pattern dirPattern = Pattern.compile("^(/\\w+/)+$");
            Pattern extPattern = Pattern.compile("(\\*\\.)?(\\w+)$");

            for (String extension : extensions) {
                Matching matching;
                if (dirPattern.matcher(extension).matches()) {
                    matching = new MtStart(extension);
                } else if (extPattern.matcher(extension).matches()) {
                    matching = new MtEnd(extension);
                } else {
                    matching = new Matching.False();
                }

                if (matching.matches(req)) {
                    return true;
                }
            }

            return false;
        });
    }
}
