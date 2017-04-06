package ru.ssau.pokubuy.core.web.misc;

import ru.ssau.pokubuy.core.web.Attribute;
import ru.ssau.pokubuy.core.web.RequestMapping;

import javax.servlet.http.HttpServletRequest;

public final class RequestWithAttributes implements RequestMapping<HttpServletRequest> {
    private final Iterable<Attribute> attributes;

    public RequestWithAttributes(Iterable<Attribute> attributes) {
        this.attributes = attributes;
    }

    @Override
    public HttpServletRequest map(HttpServletRequest request) {
        for (Attribute attribute : this.attributes) {
            request.setAttribute(attribute.name(), attribute.value());
        }

        return request;
    }
}
