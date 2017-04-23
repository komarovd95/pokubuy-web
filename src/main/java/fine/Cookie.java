package fine;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.StringJoiner;

public final class Cookie {
    private final String name;
    private final String value;
    private final Instant expires;

    public Cookie(final String name, final String value, final Instant expires) {
        this.name = name;
        this.value = value;
        this.expires = expires;
    }

    public String name() {
        return this.name;
    }

    public String value() {
        return this.value;
    }

    public Instant expires() {
        return this.expires;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("; ");

        joiner.add(this.name + "=" + this.value);
        joiner.add("expires=" + this.formatExpires());

        return joiner.toString();
    }

    private String formatExpires() {
        return ZonedDateTime.ofInstant(this.expires, ZoneId.of("GMT"))
                .format(DateTimeFormatter.ofPattern(
                        "EEE, dd MMM yyyy HH:mm:ss zzz",
                        Locale.ENGLISH
                ));
    }
}
