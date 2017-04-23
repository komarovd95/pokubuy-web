package ru.ssau.pokubuy.web.endpoints;

import ru.ssau.pokubuy.web.Matching;
import ru.ssau.pokubuy.web.Request;
import ru.ssau.pokubuy.web.Response;
import ru.ssau.pokubuy.web.matchings.MtExtension;
import ru.ssau.pokubuy.web.matchings.MtOr;

import java.util.Arrays;
import java.util.stream.Collectors;

public final class EpStatic extends EpMatching {
    public EpStatic(String... extensions) {
        this(
            Arrays.stream(extensions)
                    .map(MtExtension::new)
                    .collect(Collectors.toList())
        );
    }

    public EpStatic(Matching... matchings) {
        this(Arrays.asList(matchings));
    }

    public EpStatic(Iterable<Matching> matchings) {
        super(new MtOr(matchings));
    }

    @Override
    public Response act(Request request) {
        return request.serve();
    }
}
