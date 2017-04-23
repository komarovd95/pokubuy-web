package ru.ssau.pokubuy.web.endpoints;

import ru.ssau.pokubuy.web.Attribute;
import ru.ssau.pokubuy.web.Matching;
import ru.ssau.pokubuy.web.Request;
import ru.ssau.pokubuy.web.Response;
import ru.ssau.pokubuy.web.matchings.MtConst;
import ru.ssau.pokubuy.web.matchings.MtGet;
import ru.ssau.pokubuy.web.requests.RqAttributed;

import java.util.Arrays;

public final class EpPage extends EpMatching {
    private final String path;
    private final Iterable<Attribute<?>> attributes;

    public EpPage(String path, Attribute<?>... attributes) {
        this(new MtConst.True(), path, attributes);
    }

    public EpPage(String path, Iterable<Attribute<?>> attributes) {
        this(new MtConst.True(), path, attributes);
    }

    public EpPage(Matching matching, String path,
                  Attribute<?>... attributes) {
        this(matching, path, Arrays.asList(attributes));
    }

    public EpPage(Matching matching, String path,
                  Iterable<Attribute<?>> attributes) {
        super(new MtGet(matching));

        this.path = path;
        this.attributes = attributes;
    }

    @Override
    public Response act(Request request) {
        return new RqAttributed(
            request,
            this.attributes
        ).forward(this.path);
    }
}
