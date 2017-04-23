package fine;

import java.io.*;
import java.util.*;

public final class RqForm extends RqWrap {
    public RqForm(final Request origin) {
        super(origin);
    }

    public Map<String, String> values() throws IOException {
        if (this.checkValidRequest()) {
            QueryString queryString = new QueryString(this.formData());

            Map<String, String> formData = new HashMap<>();
            for (Map.Entry<String, List<String>> entry : queryString.asMap().entrySet()) {
                if (entry.getValue().size() > 0) {
                    formData.put(entry.getKey(), entry.getValue().get(0));
                } else {
                    formData.put(entry.getKey(), "");
                }
            }

            return formData;
        }

        return Collections.emptyMap();
    }

    public String[] values(final String... names) throws IOException {
        if (this.checkValidRequest()) {
            Map<String, List<String>> queryParams = new QueryString(this.formData()).asMap();
            String[] values = new String[names.length];

            for (int i = 0; i < names.length; i++) {
                List<String> queryValues = queryParams.get(names[i]);
                if (queryValues.size() > 0) {
                    values[i] = queryValues.get(0);
                } else {
                    values[i] = "";
                }
            }

            return values;
        }

        return new String[0];
    }

    public Optional<String> value(final String name) throws IOException {
        return new QueryString(this.formData()).single(name);
    }

    private boolean checkValidRequest() {
        Optional<Header> header = new RqHeaders(this.origin)
                .header("Content-Type");
        return header.isPresent() && header.get().value()
                .equals("application/x-www-form-urlencoded");
    }

    private String formData() throws IOException {
        try (final InputStream body = this.body();
             final ByteArrayOutputStream bytes = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = body.read(buffer)) != -1) {
                bytes.write(buffer, 0, bytesRead);
            }

            bytes.flush();

            return new String(bytes.toByteArray(), "UTF-8");
        }
    }
}
