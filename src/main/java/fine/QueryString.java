package fine;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.*;

public final class QueryString {
    private final String queryString;

    public QueryString(final URI uri) {
        this(uri.getQuery());
    }

    public QueryString(final String queryString) {
        this.queryString = queryString;
    }

    public Map<String, List<String>> asMap() {
        Map<String, List<String>> queryPairs = new HashMap<>();

        for (String pair : this.pairs()) {
            Optional<String> key = this.key(pair);
            key.ifPresent(s -> queryPairs.putIfAbsent(s, new ArrayList<>()));

            Optional<String> value = this.value(pair);
            if (key.isPresent() && value.isPresent()) {
                queryPairs.get(key.get()).add(value.get());
            }
        }

        return queryPairs;
    }

    private String[] pairs() {
        if (this.queryString == null) {
            return new String[0];
        } else {
            return this.queryString.split("&");
        }
    }

    private Optional<String> key(final String pair) {
        int index = pair.indexOf('=');
        if (index > 0) {
            try {
                return Optional.of(URLDecoder.decode(pair.substring(0, index), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                return Optional.empty();
            }
        } else {
            return Optional.of(pair);
        }
    }

    private Optional<String> value(final String pair) {
        int index = pair.indexOf('=');
        if (index > 0 && pair.length() > index + 1) {
            try {
                return Optional.of(URLDecoder.decode(pair.substring(index + 1), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    public List<String> values(final String paramName) {
        List<String> values = new ArrayList<>();

        for (String pair : this.pairs()) {
            Optional<String> key = this.key(pair);
            if (key.isPresent() && key.get().equalsIgnoreCase(paramName)) {
                this.value(pair).ifPresent(values::add);
            }
        }

        return values;
    }

    public Optional<String> single(final String paramName) {
        for (String pair : this.pairs()) {
            Optional<String> key = this.key(pair);
            if (key.isPresent() && key.get().equalsIgnoreCase(paramName)) {
                Optional<String> value = this.value(pair);
                if (value.isPresent()) {
                    return value;
                }
            }
        }

        return Optional.empty();
    }

    public Set<String> parameters() {
        Set<String> parameters = new HashSet<>();

        for (String pair : this.pairs()) {
            this.key(pair).ifPresent(parameters::add);
        }

        return parameters;
    }

    @Override
    public String toString() {
        return this.queryString;
    }
}
