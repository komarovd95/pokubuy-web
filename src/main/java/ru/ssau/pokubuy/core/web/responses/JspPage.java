package ru.ssau.pokubuy.core.web.responses;

import ru.ssau.pokubuy.core.web.Attribute;
import ru.ssau.pokubuy.core.web.Outcome;
import ru.ssau.pokubuy.core.web.Response;
import ru.ssau.pokubuy.core.web.misc.RequestWithAttributes;
import ru.ssau.pokubuy.core.web.misc.RequestDispatch;
import ru.ssau.pokubuy.core.web.outcomes.Forward;
import ru.ssau.pokubuy.core.web.outcomes.ModifiedRequestOutcome;

import java.util.ArrayList;

public final class JspPage implements Response {
    private final String pageUri;
    private final Iterable<Attribute> attributes;

    public JspPage(String pageUri) {
        this(pageUri, new ArrayList<>());
    }

    public JspPage(String pageUri, Iterable<Attribute> attributes) {
        this.pageUri = pageUri;
        this.attributes = attributes;
    }

    @Override
    public Outcome render() {
        return new ModifiedRequestOutcome(
            new Forward(
                new RequestDispatch(this.pageUri)
            ),
            new RequestWithAttributes(this.attributes)
        );
    }
}
