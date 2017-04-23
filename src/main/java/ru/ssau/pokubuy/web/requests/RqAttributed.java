package ru.ssau.pokubuy.web.requests;

import ru.ssau.pokubuy.web.*;
import ru.ssau.pokubuy.web.responses.RsAttributed;
import ru.ssau.pokubuy.web.responses.RsComposite;

import java.util.Arrays;

public final class RqAttributed implements Request {
    private final transient Request origin;
    private final Iterable<Attribute<?>> attributes;

    public RqAttributed(Request origin, Attribute<?>... attributes) {
        this(origin, Arrays.asList(attributes));
    }

    public RqAttributed(Request origin, Iterable<Attribute<?>> attributes) {
        this.origin = origin;
        this.attributes = attributes;
    }

    @Override
    public Response forward(String path) {
        return new RsComposite(
            new RsAttributed(this.attributes),
            this.origin.forward(path)
        );
    }

    @Override
    public Response serve() {
        return new RsComposite(
            new RsAttributed(this.attributes),
            this.origin.serve()
        );
    }

    @Override
    public String header(String name) {
        return this.origin.header(name);
    }

    @Override
    public boolean matches(Matching matching) {
        return this.origin.matches(matching);
    }
}
